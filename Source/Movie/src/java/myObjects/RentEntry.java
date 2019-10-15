/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package myObjects;

/**
 *
 * @author ido
 */
public class RentEntry 
{
    private int userId;
    private int copyId;
    private long rentTime;

    public RentEntry(int userId, int copyId, long rentTime) {
        this.userId = userId;
        this.copyId = copyId;
        this.rentTime = rentTime;
    }

    public RentEntry() {
    }

    public int getCopyId() {
        return copyId;
    }

    public void setCopyId(int copyId) {
        this.copyId = copyId;
    }

    public long getRentTime() {
        return rentTime;
    }

    public void setRentTime(long rentTime) {
        this.rentTime = rentTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    
    
    
    
    
}
