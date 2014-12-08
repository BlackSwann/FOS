class TableStatesController < ApplicationController
  before_action :set_table_state, only: [:show, :edit, :update, :destroy]

  # GET /table_states
  # GET /table_states.json
  def index
    @table_states = TableState.all
  end

  # GET /table_states/1
  # GET /table_states/1.json
  def show
  end

  # GET /table_states/new
  def new
    @table_state = TableState.new
  end

  # GET /table_states/1/edit
  def edit
  end

  # POST /table_states
  # POST /table_states.json
  def create
    @table_state = TableState.new(table_state_params)

    respond_to do |format|
      if @table_state.save
        format.html { redirect_to @table_state, notice: 'Table state was successfully created.' }
        format.json { render :show, status: :created, location: @table_state }
      else
        format.html { render :new }
        format.json { render json: @table_state.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /table_states/1
  # PATCH/PUT /table_states/1.json
  def update
    respond_to do |format|
      if @table_state.update(table_state_params)
        format.html { redirect_to @table_state, notice: 'Table state was successfully updated.' }
        format.json { render :show, status: :ok, location: @table_state }
      else
        format.html { render :edit }
        format.json { render json: @table_state.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /table_states/1
  # DELETE /table_states/1.json
  def destroy
    @table_state.destroy
    respond_to do |format|
      format.html { redirect_to table_states_url, notice: 'Table state was successfully destroyed.' }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_table_state
      @table_state = TableState.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def table_state_params
      params.require(:table_state).permit(:name)
    end
end
