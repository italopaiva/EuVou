class SubatividadesController < ApplicationController
  before_action :set_subatividade, only: [:show, :edit, :update, :destroy]

  # GET /subatividades
  # GET /subatividades.json
  def index
    @subatividades = Subatividade.all
  end

  # GET /subatividades/1
  # GET /subatividades/1.json
  def show
  end

  # GET /subatividades/new
  def new
    @subatividade = Subatividade.new
  end

  # GET /subatividades/1/edit
  def edit
  end

  # POST /subatividades
  # POST /subatividades.json
  def create
    @subatividade = Subatividade.new(subatividade_params)

    respond_to do |format|
      if @subatividade.save
        format.html { redirect_to @subatividade, notice: 'Subatividade was successfully created.' }
        format.json { render :show, status: :created, location: @subatividade }
      else
        format.html { render :new }
        format.json { render json: @subatividade.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /subatividades/1
  # PATCH/PUT /subatividades/1.json
  def update
    respond_to do |format|
      if @subatividade.update(subatividade_params)
        format.html { redirect_to @subatividade, notice: 'Subatividade was successfully updated.' }
        format.json { render :show, status: :ok, location: @subatividade }
      else
        format.html { render :edit }
        format.json { render json: @subatividade.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /subatividades/1
  # DELETE /subatividades/1.json
  def destroy
    @subatividade.destroy
    respond_to do |format|
      format.html { redirect_to subatividades_url, notice: 'Subatividade was successfully destroyed.' }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_subatividade
      @subatividade = Subatividade.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def subatividade_params
      params.require(:subatividade).permit(:name, :description, :duration, :location, :progress)
    end
end
