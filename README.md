# OnReceivedIncorrectErrors

This is meant to demonstrate bug reported at https://bugs.chromium.org/p/chromium/issues/detail?id=1420515. Demo has three parts

1) Application that loads up the WebView and URL
2) Website that it loads up - https://western-slow-arthropod.glitch.me (feel free to remix it, if you want to play around). You can also use any other live website that installs Service Workers
3) Fake service worker code (https://western-slow-arthropod.glitch.me/service-worker-np.js) that is loaded via APK by intercepting installation request via ServiceWorkerClient#shouldIntercept call. This is simple code that enables navigation preload and has basic fetch handler.

# Steps to repro
1. Run the application on a device. Let it load the website and ensure Service Worker is installed
2. Now click reload multiple times. 
3. Observe onReceivedError was reported even though page was loaded just fine. In case you have any proxy, you can confirm there too. 

# Tips for repro
We believe it is timing issue, so play around with server side delays in glitch project by adjusting 50ms at a time. Depending on how far you are from the server, we have noticed it needs some tweaking to get this repro.
