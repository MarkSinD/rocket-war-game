Introducing the Scrolling Shooter project
The player's objective in this game is simply to destroy as many aliens as possible.
Let's go in to some more details about the features the game will have. Look at the
starting screen for the Scrolling Shooter project in the next image:

![2](https://user-images.githubusercontent.com/33195675/130058189-4a91b265-5507-491f-a699-e9c435b2962f.jpg)


You can see there is a background of silhouetted skyscrapers. This background will
smoothly and speedily scroll in the direction the player is flying. The player can fly
left or right, and the background will scroll accordingly. However, the player cannot
stay still horizontally. You can see that when the game is over (or just been launched
by the player) you can see the message Press Play.

![1](https://user-images.githubusercontent.com/33195675/130058106-92bf6d39-0341-407b-bce6-976d63f48590.jpg)

This next image shows a particle effect explosion. These occur when an enemy ship is
hit by one of the player's lasers. I opted to not create a particle effect when the player
is destroyed because refocussing on the ship after a death is quite important and
a particle effect distracts from this.
This next image (which wasn't easy to capture) shows almost every game object in
action. The only missing item is the enemy lasers which are the same in appearance
as the player's lasers except they are red instead of green. The wide range of enemies
is one of the features of this game. We will have three different enemies with
different appearances, properties, and even different behaviors.
How we handle this complexity without our code turning into a maze of
spaghetti-like text will be one of the key learning points. We will use some
design patterns to achieve this.
