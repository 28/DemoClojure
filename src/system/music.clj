;;Music control namespace
(ns system.music
  (:import (java.io File)
           (javax.sound.sampled Clip AudioFormat AudioInputStream AudioSystem DataLine DataLine$Info AudioFileFormat AudioFileFormat$Type)))

(defn play-sound
  "Plays the given wave file."
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
  "Playes a part of given wave file specified by 
   startsecond and secondtoplay parameters."
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
  "Returns a wave file that contains the first 30s of the given file."
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
  "Returns a croped wave file.Amount to crop is specified by the
   startsecond and secondtocut parameters."
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