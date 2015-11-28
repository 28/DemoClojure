# democlojure

Version: 1.0.0

This is a Clojure REPL application for creating and storing ringtones. This is actually a simulation
of the console app (like in terminal or cmd). Also application simulates creating an user account (signing
in), logging in to that account and creating ringtones using that account.

It's called democlojure (for a lack of a better name) because it's my first Clojure application and
it was my first Clojure assignment for school.

The further development of this application is stopped except for maybe some minor adjustments.

## Usage

This is a REPL application so start a repl session using <a href="http://leiningen.org/">Leiningen</a>
like this: ```lein repl```.

To create an user account:
```clojure
(sign-console)
Enter your first name:
Test
Enter your last name:
Test
Enter your username:
Test
Enter your password:
test
Confirm your password:
test
Enter your email:
test@test.com
Success!
nil
```

After this account is created and and to create ringtones user must be logged in:
```clojure
(log-in-console)
Enter username:
Test
Enter password:
test
Success, you are now logged in!
nil
```

Now that an user is logged in, it has access to two main functionalities, playing
music files and creating ringtones from music files (cropping). Only ```.wav``` files
are supported.

To play the music file:
```clojure
(play-console)
Enter path to file:
C:\Test\test.wav
Start second:
0
Seconds to play:
5
nil
```

To create the ringtone:
```clojure
(crop-console)
Enter path to file:
C:\Test\test.wav
Enter target path:
C:\Test\t.wav
Start second:
0
Amount to cut:
14
Success!
nil
```

Ringtone is created and saved to the targer file.

At the end user can log out:
```clojure
(log-out-console)
Enter username:
Test
You logged out.
nil
```

## License

Distributed under the Eclipse Public License, the same as Clojure.

Copyright &copy; Dejan Josifović 2013.
