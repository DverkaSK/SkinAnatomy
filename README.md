# SkinAnatomy

![gif](demo.gif)

SkinAnatomy is a plugin to edit every part of a player's skin

An API is available to interact with the skin at the code level

## Features
<ul>
    <li><strong>Edit each of the 6 parts of the skin (head, body, arms, legs)</strong></li>
    <li><strong>Edit each side of any part</strong></li>
</ul>

<strong>Imgur API token is required for correct operation</strong>.

## Commands

<ul>
    <li>/skinanatomy - info about usage</li>
</ul>

## Permissions

<ul>
    <li>skinanatomy.use - for all user commands & use</li>
</ul>

## Configuration

Adventure API is support

```yaml
skinanatomy:
  messages:
    loadSuccess:
      head: "<gradient:#00c9ff:#92fe9d>Head loaded successfully!</gradient>"
      body: "<gradient:#00c9ff:#92fe9d>Body loaded successfully!</gradient>"
      leftHand: "<gradient:#00c9ff:#92fe9d>Left hand loaded successfully!</gradient>"
      rightHand: "<gradient:#00c9ff:#92fe9d>Right hand loaded successfully!</gradient>"
      leftLeg: "<gradient:#00c9ff:#92fe9d>Left leg loaded successfully!</gradient>"
      rightLeg: "<gradient:#00c9ff:#92fe9d>Right leg loaded successfully!</gradient>"
    errors:
      commandUsage: "<red>Usage /skinanatomy <get|set> <head|body|lefthand|righthand|leftleg|rightleg> <url></red>"
      noPermission: "<red>You don't have permission for /skinanatomy</red>"
      noPlayerSkin: "<red>You don't have a skin</red>"
      skinByNicknameNotFound: "<red>Skin by nickname not found</red>"
      errorLoadingSkin: "<red>Error loading skin</red>"
  defaultSkinURL: ""
  imgurAccessToken: ""
```