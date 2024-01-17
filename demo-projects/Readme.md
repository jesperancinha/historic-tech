# Demo projects

## Video Studio Scripts

##### Debian/Ubuntu Systems

1.  [Linux studio Install Bash Script](./linux-video-studio-install.sh)

## References

-   [Filmoras To 10 Open Source editors](https://filmora.wondershare.com/video-editor/free-linux-video-editor.html)
-   [Podcast enhance](https://podcast.adobe.com/enhance)
-   [Using a Canon DSLR as a webcam with Debian/Ubuntu](https://maximevaillancourt.com/blog/canon-dslr-webcam-debian-ubuntu)
-   [G'MIC plugin for GIMP](https://gmic.eu/download.html)
-   [Fake Whatsup](https://www.fakewhats.com/generator)
-   [Vidyo AI](https://app.vidyo.ai/)

## Recording Linux tips

```shell
sudo apt-get reinstall gphoto2 v4l2loopback-utils v4l2loopback-dkms ffmpeg build-essential libelf-dev linux-headers-$(uname -r) unzip vlc
sudo modprobe v4l2loopback
gphoto2 --stdout --capture-movie | ffmpeg -i - -vcodec rawvideo -pix_fmt yuv420p -threads 0 -f v4l2 /dev/video4
vlc v4l2:///dev/video4
streamer -t 0:1:0 -c /dev/video4 -f rgb24 -r 60 -o test.avi -C /dev/dsp4
```

```shell
sudo apt-get install alsa-utils
pulseaudio --start
```

```shell
sudo apt-get install ffmpeg
ffmpeg -i $INPUT.mp4 $OUTPUT.mp3
```

```shell
sudo apt-get install gimp-gmic
```

## Ideal prompt with ZSH

#### 1.  For videos
```shell
PS1='%1d$ '
```

## About me

[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=Jesperancinha&style=for-the-badge&logo=github&color=grey "GitHub")](https://github.com/jesperancinha)
