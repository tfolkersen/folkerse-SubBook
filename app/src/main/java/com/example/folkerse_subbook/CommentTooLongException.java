//CommentTooLongException

package com.example.folkerse_subbook;

/**
 * @author folkerse
 * @version 1.0
 * @see Subscription
 *
 * Exception thrown when comment is too long
 */
public class CommentTooLongException extends Exception {
    private String comment;

    public CommentTooLongException(String comment) {
        this.comment = comment;
    }

    /**
     * @return Message explaining that the comment is too long, includes its length
     */
    @Override
    public String toString() {
        return "Comment Too Long: " + comment.length();
    }


}
