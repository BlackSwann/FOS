json.array!(@table_states) do |table_state|
  json.extract! table_state, :id, :name
  json.url table_state_url(table_state, format: :json)
end
