package com.example.kandoe.Utilities.API;

import com.example.kandoe.Model.Card;
import com.example.kandoe.Model.ChatMessage;
import com.example.kandoe.Model.Organisation;
import com.example.kandoe.Model.Session;
import com.example.kandoe.Model.Snapshot;
import com.example.kandoe.Model.SubTheme;
import com.example.kandoe.Model.Theme;
import com.example.kandoe.Model.UserAccount;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Thomas on 2016-02-16.
 */
public interface KandoeBackendAPI {

    //SelectionCard
    @GET("api/selection-cards")
    Call<List<Card>> getSelectionCards();

    @GET("api/selection-cards/{id}")
    Call<List<Card>> getSelectionCardsById(@Path("id") int id);

    @GET("api/selection-cards/by-subtheme/{id}")
    Call<List<Card>> getSelectionCardsBySubthemeId(@Path("id") int id);

    //Create new card
    @POST("api/selection-cards")
    Call<Card> addCard(@Body Card card);

    //SessionCard
    //Using already existing cards, add to the specified session.
    @PATCH("api/sessions/{id}/select-cards")
    Call<Void> addCardsToSession(@Path("id") int id, @Body List<Card> cards);

    //Get position of a specified card in a specified session.
    @GET("api/cards/{id}/position/{sessionid}")
    Call<Integer> getPositionInSession(@Path("id") int cardId, @Path("sessionid") int sessionId);

    @PATCH("api/sessions/{id}/join")
    Call<Void> addPlayerToSession(@Path("id") int id);


    //Account
    @GET ("api/accounts/{id}")
    Call<UserAccount> getUserById(@Path("id") int id);

    @POST("api/accounts")
    Call<UserAccount> postUser(@Body UserAccount account);

    @PATCH("api/accounts")
    Call<Void> changeAccount(@Body UserAccount account);

    @GET("api/accounts/by-auth0-user-id/{secret}")
    Call<UserAccount> getUserId(@Path("secret") String secret);

    //Session
    @GET ("api/sessions")
    Call<List<Session>> getAllSessions();

    @GET("api/sessions/{id}")
    Call<Session> getSessionById(@Path("id") int id);

    @GET ("api/sessions/by-organisation/{id}")
    Call<List<Session>> getSessionsByOrganisation(@Path("id") int id);

    @GET ("api/sessions/by-subtheme/{id}")
    Call<List<Session>> getSessionsBySubtheme(@Path("id") int id);

    @GET ("api/sessions/by-start-date/{date}")
    Call<List<Session>> getSessionsByStartDate(@Path("date") Date date);

    @GET ("api/sessions/by-end-date/{date}")
    Call<List<Session>> getSessionsByEndDate(@Path("date") Date date);

    @GET ("api/verbose/sessions/{id}")
    Call<Session> getVerboseSessionById(@Path("id") int id);

    @PATCH ("api/sessions/{sessionId}/level-up-card/{cardId}")
    Call<Void> levelUpCard(@Path("sessionId") int sessionId,@Path("cardId") int cardId);

    //Organisation
    @GET("api/organisations")
    Call<List<Organisation>> getOrganisations();

    //Get all organisations and all nested collections.
    @GET("api/verbose/organisations")
    Call<List<Organisation>> getOrganisationsVerbose();

    @GET("api/organisations/{id}")
    Call<Organisation> getOrganisationById(@Path("id") int id);

    @GET("api/organisations/by-organiser/{id}")
    Call<List<Organisation>> getOrganisationsByOrganiserId(@Path("id") int id);


    //ChatMessage
    @GET("api/chat-messages")
    Call<List<ChatMessage>> getChatMessages();

    @GET("api/chat-messages/{id}")
    Call<ChatMessage> getChatById(@Path("id") int id);

    @POST("api/chat-messages")
    Call<Void> postChatMessages(@Body ChatMessage chatmessage);

    @GET ("api/chat-messages/by-session/{id}")
    Call<List<ChatMessage>> getChatMessagesBySessionId(@Path("id") int id);


    //Theme
    @GET("api/themes")
    Call<List<Theme>> getThemes();

    @GET("api/themes/{id}")
    Call<Theme> getThemesById(@Path("id") int id);

    @GET ("api/themes/by-organisation/{id}")
    Call<Theme> getThemeByOrganisationId(@Path("id") int id);

    @GET ("api/themes/by-tag/{tag}")
    Call<List<Theme>>getThemesByTag(@Path("tag") String tag);


    //Subtheme
    @GET("api/subThemes")
    Call<List<SubTheme>> getSubThemes();

    @GET("api/subThemes/{id}")
    Call<SubTheme> getSubThemeById(@Path("id") int id);

    @GET("api/subthemes/by-theme/{id}")
    Call<List<SubTheme>>getSubthemesByThemeId(@Path("id") int id);

    //Snapshot
    @GET ("api/snapshots/{id}")
    Call<Snapshot> getSnapshotById(@Path("id") int id);

    @GET ("api/snapshots/by-session/{id}")
    Call<List<Snapshot>> getSnapshotsBySessionId(@Path("id") int id);
}


