;;Music control namespace
(ns system.music
  (:import (java.io File)
           (javax.sound.sampled Clip AudioFormat AudioInputStream AudioSystem DataLine DataLine$Info AudioFileFormat AudioFileFormat$Type)))

(defn play-sound
  ""
  [filename]
  (let 
    [file (File. filename)
     inputstream (AudioSystem/getAudioInputStream file)
     fileformat (. inputstream getFormat)
     info (DataLine$Info. Clip fileformat)
     clip (AudioSystem/getLine info)]
  (do
    (. clip open inputstream)
    (. clip start))))

(defn play-sound-part
  ""
  [filename startsecond secondtoplay]
  (let
    [file (File. filename)
     inputstream (AudioSystem/getAudioInputStream file)
     audioformat (. (AudioSystem/getAudioFileFormat file) getFormat)
     info (DataLine$Info. Clip audioformat)
     clip (AudioSystem/getLine info)
     bytespersecond (int (* (. audioformat getFrameSize) (int (. audioformat getFrameRate))))
     framestoplay (long (* secondtoplay (int (. audioformat getFrameRate))))
     newstream (do
                 (. inputstream skip (* startsecond bytespersecond))
                 (AudioInputStream. inputstream audioformat framestoplay))]
    (do
      (. clip open newstream)
      (. clip start))))

(defn crop30s
  ""
  [filename targetfile]
  (let
    [file (File. filename)
     fileformat (AudioSystem/getAudioFileFormat file)
     audioformat (. fileformat getFormat)
     inputstream (AudioSystem/getAudioInputStream file)
     bytespersecond (int (* (. audioformat getFrameSize) (int (. audioformat getFrameRate))))
     framestocopy (long (* 30 (int (. audioformat getFrameRate))))
     shortstream (do
                   (. inputstream skip (* 1 bytespersecond))
                   (AudioInputStream. inputstream audioformat framestocopy))
     outfile (File. targetfile)]
    (AudioSystem/write shortstream (. fileformat getType) outfile)))

(defn crop
  ""
  [filename targetfile startsecond secondstocut]
  (let
    [file (File. filename)
     fileformat (AudioSystem/getAudioFileFormat file)
     audioformat (. fileformat getFormat)
     inputstream (AudioSystem/getAudioInputStream file)
     bytespersecond (int (* (. audioformat getFrameSize) (int (. audioformat getFrameRate))))
     framestocopy (long (* secondstocut (int (. audioformat getFrameRate))))
     shortstream (do
                   (. inputstream skip (* startsecond bytespersecond))
                   (AudioInputStream. inputstream audioformat framestocopy))
     outfile (File. targetfile)]
    (AudioSystem/write shortstream (. fileformat getType) outfile)))

;;End of file system.music