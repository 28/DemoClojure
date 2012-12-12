(ns system.DBManagement)

(def db (atom {}))

(defn add-to-db
  ""
  [firstname lastname username password email]
  (swap! db assoc (keyword username) (atom 
                                       {:username (ref username)
                                        :password (ref password)
                                        :email (ref email)
                                        :firstname (ref firstname)
                                        :lastname (ref lastname)})))

(defn exists?
  ""
  [username]
  (if
    (not 
      (nil? 
        (@db (keyword username)))) true false))

(defn return
  ""
  [username comm]
  (if 
    (not 
        (nil? comm))
    @(@(@db (keyword username)) (keyword comm))  
    (let
      [u @(@(@db (keyword username)) :username)
      f @(@(@db (keyword username)) :firstname)
      l @(@(@db (keyword username)) :lastname)
      e @(@(@db (keyword username)) :email)
      p @(@(@db (keyword username)) :password)]
      {:username u :password p :firstname f :lastname l :email e}
      )))










 