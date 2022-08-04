package fx.fixgia.userms.model;

public class Role {

   public Role() {

   }

    /**
     * The Constant Role SuperAdmin
     * Everything is possible !
     */
   public final static String SUPERADMIN = "SuperAdmin";
    /**
     * The Constant Role Admin
     * Admin can user the apply, add User and CRUD the Protocols
     */
   public final static String ADMIN = "Admin" ;

    /**
     * The Constant Role User
     * User can use the apply but can't add User or protocols
     */
   public final static String USER = "User" ;
    /**
     * The Constant Role Viewer
     *  Viewer can't change anything, just watch !
     */
   public final static String VIEWER = "Viewer" ;
}
