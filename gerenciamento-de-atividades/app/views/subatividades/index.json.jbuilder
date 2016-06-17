json.array!(@subatividades) do |subatividade|
  json.extract! subatividade, :id, :name, :description, :duration, :location, :progress
  json.url subatividade_url(subatividade, format: :json)
end
