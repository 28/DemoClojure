(ns system.Main
  (:use system.DBManagement)
  (:use system.SessionManagement))

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
  []
  )