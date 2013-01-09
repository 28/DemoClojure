;;This is the business logic of the system.
(ns system.main
  (:use system.dbmanagement)
  (:use system.sessionmanagement)
  (:use system.music))

(defn sign-up
  ""
  [firstname lastname username password email]
  (if (and 
        (not 
          (nil? 
            (add-to-db firstname lastname username password email))) 
        (true? true));;TODO
  true
  false))

(defn logged?
  ""
  [username]
  (if (contains? @session (keyword username))
    true
    false))

(defn log-in
  ""
  [username password]
  (if (and 
        (exists? username)
        (= password (return username "password"))
        (not (logged? username)))
    (if (not (nil? (add-to-session username)))
      true
      false)
    false))

(defn log-out
  ""
  [username]
  (remove-from-session username))

(defn play
  ""
  [filename s e]
 (if
    (and (nil? s)
          (nil? e))
      (play-sound filename)
      (play-sound-part filename s e)))

(defn cropcomb
  ""
  [filename target s a]
  (if
    (and (nil? s)
          (nil? a))
      (crop30s filename target)
      (crop filename target s a)))

(defn form-ok?
  "TODO"
  [a & args]
  )

;;End of file system.main