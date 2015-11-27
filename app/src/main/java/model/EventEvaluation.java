package model;

/**
 * Created by marlonmendes on 15/11/15.
 */
public class EventEvaluation {
    private Float rating;
    private Integer userId;
    private Integer eventId;


    public EventEvaluation(Float rating, Integer userId, Integer eventId) {
        setRating(rating);
        setUserId(userId);
        setEventId(eventId);
    }

    public Float getRating() {
        return rating;
    }

    private void setRating(Float rating) {
        this.rating = rating;
    }

    public Integer getUserId() {
        return userId;
    }

    private void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }
}
