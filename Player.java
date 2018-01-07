import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObjekt {
	
	Handler handler;
	Game game;

	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.game = game;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		collision();
		
		//movement
		if(handler.isUp()) velY = -7;
		else if(!handler.isDown()) velY = 0;
		
		if(handler.isDown()) velY = 7;
		else if(!handler.isUp()) velY = 0;
		
		if(handler.isRight()) velX = 7;
		else if(!handler.isLeft()) velX = 0;
		
		if(handler.isLeft()) velX = -7;
		else if(!handler.isRight()) velX = 0;
		
	}

	private void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			
			GameObjekt tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Block) {
				
				if(getBounds().intersects(tempObject.getBounds())) {
					x += velX * -1;
					y += velY * -1;
				}
				
			}
			
            if(tempObject.getId() == ID.Crate) {
				
				if(getBounds().intersects(tempObject.getBounds())) {
					game.ammo += 100;
					handler.removeObject(tempObject);
				}
				
			}
			
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, 32, 48);
		
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 48);
	}

}
