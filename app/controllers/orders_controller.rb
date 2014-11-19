class OrdersController < ApplicationController
  before_action :set_order, only: [:show, :edit, :update, :destroy]

  # GET /orders
  # GET /orders.json
  def index
    @orders = Order.all
  end

  # GET /orders/1
  # GET /orders/1.json
  def show
  end

  # GET /orders/new
  def new
    @states = OrderState.all
    @products = Product.all
    @order = Order.new
  end

  # GET /orders/1/edit
  def edit
    @states = OrderState.all
  end

  # POST /orders
  # POST /orders.json
  def create
    @order = Order.new(order_params)
    @order.order_state = OrderState.find_by(name: 'Ordered')
    @order.final_price = 0
    for detail in @order.order_details
	    @order.final_price = @order.final_price + (detail.amount * detail.product.price)
    end

    respond_to do |format|
      if @order.save
        format.html { redirect_to @order, notice: 'Order was successfully created.' }
        format.json { render :show, status: :created, location: @order }
	format.js   { render action: 'show', status: :created, location: @oder }
      else
        format.html { render :new }
        format.json { render json: @order.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /orders/1
  # PATCH/PUT /orders/1.json
  def update
    respond_to do |format|
      if @order.update(order_params)
        format.html { redirect_to @order, notice: 'Order was successfully updated.' }
        format.json { render :show, status: :ok, location: @order }
      else
        format.html { render :edit }
        format.json { render json: @order.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /orders/1
  # DELETE /orders/1.json
  def destroy
    @order.destroy
    respond_to do |format|
      format.html { redirect_to orders_url, notice: 'Order was successfully destroyed.' }
      format.json { head :no_content }
    end
  end

  def take_orders
	  @orders = Order.all.where('order_state_id =? OR order_state_id =?', OrderState.find_by(name: 'Ordered'), OrderState.find_by(name: 'Delivered')) 
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_order
      @order = Order.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def order_params
      params.require(:order).permit(:final_price, :order_state_id, order_details_attributes: [:id, :name, :amount, :product_id, :_destroy])
    end
end
