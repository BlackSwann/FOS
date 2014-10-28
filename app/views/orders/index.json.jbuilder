json.array!(@orders) do |order|
  json.extract! order, :id, :final_price, :order_state_id
  json.url order_url(order, format: :json)
end
