json.array!(@activities) do |activity|
  json.extract! activity, :id, :name, :description, :type
  json.url activity_url(activity, format: :json)
end
