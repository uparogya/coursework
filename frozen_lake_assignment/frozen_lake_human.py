"""
Source of the game: https://gymnasium.farama.org/environments/toy_text/frozen_lake/

Please install gym packages as below:
    pip install gymnasium
    pip install 'gymnasium[box2d]'
And also make sure pygame package is available in your python environment. 

How to play as a human:
(1) Use arrow keys to play the game. 
(2) Your goal is to move the agent on the ice to the goal at bottom right while avoiding felling into the holes. 
(3) Be careful, the ice board is super slippery. The agent will move in intended direction with probability of 1/3 
else will move in either perpendicular direction with equal probability of 1/3 in both directions.
(4) You may need to manually force quit the game after playing. You may modify the code to update a better quit mechanism. 
"""

import gymnasium as gym
import pygame
from pygame.locals import *

# Set up the environment
env = gym.make("FrozenLake-v1", render_mode="human", is_slippery=True)
obs, info = env.reset()

# Initialize Pygame
pygame.init()
pygame.display.set_caption("FrozenLake Human Player")

clock = pygame.time.Clock()
running = True

# Action mapping for arrow keys
key_to_action = {
    K_LEFT: 0,   # left
    K_DOWN: 1,   # down
    K_RIGHT: 2,  # right
    K_UP: 3      # up
}

while running:
    for event in pygame.event.get():
        if event.type == QUIT:
            running = False
            env.close()
            pygame.quit()
            break

        if event.type == KEYDOWN and event.key in key_to_action:
            action = key_to_action[event.key]
            obs, reward, terminated, truncated, info = env.step(action)

            if terminated or truncated:
                clock.tick(10)  # Adjust FPS
                if reward == 1:
                    print("🎉 You reached the goal!")
                else:
                    print("💀 You fell into a hole.")
                obs, info = env.reset()

    clock.tick(10)  # Adjust FPS
