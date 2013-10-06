;;This is the business logic of the system.
(ns system.main
  (:use system.dbmanagement)
  (:use system.sessionmanagement)
  (:use system.music))

(defn form-ok?
  "Returns true if entry is well formed for use."
  [entry]
  (if
    (or
      (nil? entry)
      (= entry "")
      (= entry " ")
      (number? entry))
    false
    true))

(defn sign-up
  "Handles the sign up process."
  [firstname lastname username password email]
  (if 
    (and 
        (not 
          (nil? 
            (add-to-db firstname lastname username password email))) 
        (every? form-ok? [firstname lastname username password email]))
  true
  false))

(defn logged?
  "True if user is logged on."
  [username]
  (if 
    (contains? @session (keyword username))
    true
    false))

(defn log-in
  "Handles the log in process."
  [username password]
  (if 
    (and 
        (exists? username)
        (= password (return username "password"))
        (not (logged? username)))
    (if 
      (not 
        (nil? (add-to-session username)))
      true
      false)
    false))

(defn log-out
  "Handles the log out process."
  [username]
  (remove-from-session username))

(defn play
  "Plays the whole file if s and e parameters are nil
   or part of it.Parameter s is start second and parameter
   a is amount to play, in seconds."
  [filename s a]
 (if
    (and 
      (nil? s)
      (nil? a))
    (play-sound filename)
    (play-sound-part filename s a)))

(defn cropcomb
  "Crops the amount of file specified by the s and a parameters.If
   s and a are nil function return the first 30s of the file."
  [filename target s a]
  (if
    (and 
      (nil? s)
      (nil? a))
    (crop30s filename target)
    (crop filename target s a)))