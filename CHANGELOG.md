## 1.1.0

* Add V2 Clients for Text tracks, Tags, Usage, Player Bidding Configs, Originals, Media Renditions, Thumbnails.
* Update Playlists client to include watchlist.

## 1.0.0

* Add the following V2 clients-
Advertising, Analytics, Channels, Events, Imports, Media, Playlists, Protection Rules, Uploads, VPB Configs, Webhooks
* Rename V1 client package from com.jwplayer.jwplatform.client to com.jwplayer.jwplatform.v1  _This is a Breaking change_
* Update README to include usage documentation for V2 client.

## 0.4.0

* Stop using nested exception handling
* Bump junit dependency to v4.13 for CVE patch

## 0.3.1

* Javadoc updates

## 0.3.0

* Security upgrade of puppycrawl
* Add param for headers to requests() and upload()
* Bugfix the Exception Factory

## 0.2.0

* Add support for POST request endpoints.
* Refactor exception handling such that exceptions are no longer nested classes of the factory. _This is a breaking
  change._

## 0.1.0

* Initial version of the client.

## 0.0.0

* Initial test version of the client.