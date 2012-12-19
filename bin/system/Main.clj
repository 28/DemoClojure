;;This is the business logic of the system.
(ns system.main
  (:use system.dbmanagement)
  (:use system.sessionmanagement))

(defn sign-up
  ""
  [firstname lastname username password email]
  (if (and 
        (not 
          (nil? 
            (add-to-db firstname lastname username password email))) 
        (true? true))
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

(defn form-ok?
  ""
  [a & args]
  )

;;End of file system.main