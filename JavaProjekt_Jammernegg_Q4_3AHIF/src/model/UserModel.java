
package model;

import data.UserScore;
import java.util.List;
import javax.swing.AbstractListModel;


public class UserModel extends AbstractListModel<UserScore>{

    private final List<UserScore> currentView;

    public UserModel(List<UserScore> currentView) {
         this.currentView = currentView;
    }
    
    @Override
    public int getSize() {
        return currentView.size();
    }

    @Override
    public UserScore getElementAt(int index) {
        return currentView.get(index);
    }
    
    
}
