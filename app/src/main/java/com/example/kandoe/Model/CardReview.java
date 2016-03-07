package com.example.kandoe.Model;

/**
 * Created by Michelle on 22-2-2016.
 */
public class CardReview {
    private int Id,ReviewerId,CardId;
    private String Comment;

    public CardReview(int id, int reviewerId, int cardId, String comment) {
        Id = id;
        ReviewerId = reviewerId;
        CardId = cardId;
        Comment = comment;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getReviewerId() {
        return ReviewerId;
    }

    public void setReviewerId(int reviewerId) {
        ReviewerId = reviewerId;
    }

    public int getCardId() {
        return CardId;
    }

    public void setCardId(int cardId) {
        CardId = cardId;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }
}
