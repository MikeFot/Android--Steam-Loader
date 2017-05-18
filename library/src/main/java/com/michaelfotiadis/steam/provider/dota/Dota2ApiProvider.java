package com.michaelfotiadis.steam.provider.dota;

import com.michaelfotiadis.steam.data.FileFormat;
import com.michaelfotiadis.steam.data.ResultContainer;
import com.michaelfotiadis.steam.data.dota2.model.hero.HeroesResponse;
import com.michaelfotiadis.steam.data.dota2.model.item.ItemsResponse;
import com.michaelfotiadis.steam.data.dota2.model.match.details.MatchDetailsResponse;
import com.michaelfotiadis.steam.data.dota2.model.match.history.MatchHistory;
import com.michaelfotiadis.steam.data.dota2.model.rarity.Rarities;
import com.michaelfotiadis.steam.data.dota2.types.GameMode;
import com.michaelfotiadis.steam.data.dota2.types.SkillBracket;
import com.michaelfotiadis.steam.data.games.ValveGameIds;
import com.michaelfotiadis.steam.net.api.dota2.Dota2Api;
import com.michaelfotiadis.steam.provider.SteamCallback;
import com.michaelfotiadis.steam.provider.base.ApiProvider;

import retrofit2.Call;

public class Dota2ApiProvider extends ApiProvider<Dota2Api> {

    private static final String PROD_ENDPOINT_ID = "570";
    private static final String DEV_ENDPOINT_ID = "205790";

    private final String endpointId;

    public Dota2ApiProvider(final String key, final boolean isDebugEnabled, final Dota2Api api) {
        super(key, api);

        this.endpointId = isDebugEnabled ? ValveGameIds.DOTA_2_BETA.toString() : ValveGameIds.DOTA_2.toString();

    }

    /**
     * Information about a particular match.
     *
     * @param matchId        unique identifier of the match
     * @param masterCallback {@link SteamCallback} for delivering the result
     */
    public void getMatchDetails(final String matchId,
                                final SteamCallback<MatchDetailsResponse> masterCallback) {

        final Call<MatchDetailsResponse> call = getApi().getMatchById(
                endpointId,
                getKey(),
                matchId,
                FileFormat.JSON.toString());
        execAsync(call, new WrappedCallback<>(masterCallback));

    }

    /**
     * A list of matches, filterable by various parameters.
     *
     * @param accountId       optional - 32-bit accountId
     * @param numberOfMatches optional - Amount of matches to include in results (default: 25).
     * @param masterCallback  {@link SteamCallback} for delivering the result
     */
    public void getMatchHistory(final String accountId,
                                final Integer numberOfMatches,
                                final SteamCallback<ResultContainer<MatchHistory>> masterCallback) {

        final Call<ResultContainer<MatchHistory>> call = getApi().getMatchHistory(
                endpointId,
                getKey(),
                accountId,
                null, null, null, null, null, null, null,
                numberOfMatches,
                FileFormat.JSON.toString());
        execAsync(call, new WrappedCallback<>(masterCallback));

    }

    /**
     * @param accountId           optional - 32-bit accountId
     * @param heroId              optional - include matches which contain this hero ID. A list of hero IDs can be found via the GetHeroes method.
     * @param gameMode            optional - {@link GameMode}
     * @param skillBracket        optional - {@link SkillBracket} Skill bracket for the matches (Ignored if an account ID is specified).
     * @param minPlayers          optional - Minimum amount of players in a match for the match to be returned.
     * @param leagueId            optional - Only return matches from this league. A list of league IDs can be found via the GetLeagueListing method.
     * @param startAtMatchId      optional - Start searching for matches equal to or older than this match ID.
     * @param tournamentGamesOnly optional - Whether to limit results to tournament matches. (0 = false, 1 = true)
     * @param numberOfMatches     optional - Amount of matches to include in results (default: 25).
     * @param masterCallback      {@link SteamCallback} for delivering the result
     */
    public void getMatchHistory(final String accountId,
                                final String heroId,
                                final GameMode gameMode,
                                final SkillBracket skillBracket,
                                final Integer minPlayers,
                                final String leagueId,
                                final String startAtMatchId,
                                final Boolean tournamentGamesOnly,
                                final Integer numberOfMatches,
                                final SteamCallback<ResultContainer<MatchHistory>> masterCallback) {

        final Call<ResultContainer<MatchHistory>> call = getApi().getMatchHistory(
                endpointId, getKey(),
                accountId,
                heroId,
                gameMode.getValue(),
                skillBracket.getValue(),
                minPlayers,
                leagueId,
                startAtMatchId,
                tournamentGamesOnly,
                numberOfMatches,
                FileFormat.JSON.toString());
        execAsync(call, new WrappedCallback<>(masterCallback));

    }


    /**
     * Gets matches starting at a sequence number
     *
     * @param sequenceNumber  optional - The match sequence number to start returning results from.
     * @param numberOfMatches optional - The amount of matches to return.
     * @param masterCallback  {@link SteamCallback} for delivering the result
     */
    public void getMatchHistoryBySequenceNumber(final String sequenceNumber,
                                                final Integer numberOfMatches,
                                                final SteamCallback<ResultContainer<MatchHistory>> masterCallback) {

        final Call<ResultContainer<MatchHistory>> call = getApi().getMatchHistoryBySequenceNumber(
                endpointId,
                getKey(),
                sequenceNumber,
                numberOfMatches,
                FileFormat.JSON.toString());
        execAsync(call, new WrappedCallback<>(masterCallback));

    }

    /**
     * A list of heroes within Dota 2.
     *
     * @param language       optional - request localized names e.g. en_gb
     * @param isItemizedOnly optional - ask for only itemized results
     * @param masterCallback {@link SteamCallback} for delivering the result
     */
    public void getHeroes(final String language,
                          final boolean isItemizedOnly,
                          final SteamCallback<HeroesResponse> masterCallback) {

        final Call<HeroesResponse> call = getApi().getHeroes(
                endpointId,
                getKey(),
                language,
                isItemizedOnly,
                FileFormat.JSON.toString());

        execAsync(call, new WrappedCallback<>(masterCallback));

    }

    /**
     * Dota 2 In-game items
     *
     * @param language       optional - request localized names e.g. en_gb
     * @param masterCallback {@link SteamCallback} for delivering the result
     */
    public void getItems(final String language,
                         final SteamCallback<ItemsResponse> masterCallback) {

        final Call<ItemsResponse> call = getApi().getItems(
                endpointId,
                getKey(),
                language,
                FileFormat.JSON.toString());

        execAsync(call, new WrappedCallback<>(masterCallback));

    }

    /**
     * Dota 2 item rarity list.
     *
     * @param language       optional - request localized names e.g. en_gb
     * @param masterCallback {@link SteamCallback} for delivering the result
     */
    public void getRarities(final String language,
                            final SteamCallback<Rarities> masterCallback) {

        final Call<Rarities> call = getApi().getRarities(
                endpointId,
                getKey(),
                language,
                FileFormat.JSON.toString());

        execAsync(call, new WrappedCallback<>(masterCallback));

    }


}