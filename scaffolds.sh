rails g scaffold ProductType name:string
rails g scaffold Product name:string price:float product_type:references
rails g scaffold OrderState name:string
rails g scaffold OrderDetail amount:float order:references product:references
rails g scaffold Order final_price:float order_state:references

