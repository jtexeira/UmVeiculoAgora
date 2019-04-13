import java.util.HashMap;

public class UserBase {
    HashMap<String, Users> userBase;

    public UserBase() {
        this.userBase = new HashMap<>();
    }

    /**
     * Adiciona um user ao sistema
     *
     * @param u User a adicionar
     */
    public void addUser(Users u) {
        this.userBase.put(u.getEmail(), u.clone());
    }



    /**
     * Remove um user do sistema
     * @param id Id do user a remover
     */
    public void rmUser(String id) {
        this.userBase.remove(id);
    }

    /**
     * Remove um user do sistema
     * @param id User a remover
     */
    public void rmUser(Users id) {
        this.userBase.remove(id.getEmail());
    }
}
