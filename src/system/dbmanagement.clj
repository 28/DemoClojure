;;This namespace handles communication with the database.
(ns system.dbmanagement)

(def db (atom {}))

(defn add-to-db
  "Adds a new user to database which is a atom. The function
   takes user attributes as parameters."
  [firstname lastname username password email]
  (swap! db assoc (keyword username) (ref
                                       {:username username
                                        :password password
                                        :email email
                                        :firstname firstname
                                        :lastname lastname})))

(defn exists?
  "Returns true if username exists in database and
   false if it does not."
  [username]
  (if
    (not
      (nil?
        (@db (keyword username)))) true false))

(defn return
  "Returns the user attribute specified by comm parameter. If
   comm parameter is nil function returns user data map."
  [username comm]
  (if
    (not
        (nil? comm))
    (@(@db (keyword username)) (keyword comm))
    (let
      [u (@(@db (keyword username)) :username)
      f  (@(@db (keyword username)) :firstname)
      l  (@(@db (keyword username)) :lastname)
      e  (@(@db (keyword username)) :email)
      p  (@(@db (keyword username)) :password)]
      {:username u :password p :firstname f :lastname l :email e})))
