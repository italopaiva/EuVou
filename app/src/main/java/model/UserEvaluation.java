package model;

/**
 * Created by igor on 20/11/15.
 */
public class UserEvaluation {
    private Float rating;
    private Integer userId;
    private Integer userEvaluatedId;

    public UserEvaluation(Float rating, Integer userId, Integer userEvaluatedId){
        setRating(rating);
        setUserId(userId);
        setUserEvaluatedId(userEvaluatedId);
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserEvaluatedId() {
        return userEvaluatedId;
    }

    public void setUserEvaluatedId(Integer userEvaluatedId) {
        this.userEvaluatedId = userEvaluatedId;
    }
}
