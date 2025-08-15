class parent (x0 : int) =
  object
    val x = x0
    method get_x = x
  end

class child (x0 : int) (y0 : int) =
  object
    inherit parent x0
    val y = y0
    method get_y = y
  end

class stranger (x0 : int) (y0 : int) =
  object
    val x = x0
    val y = y0
    method get_x = x
    method get_y = y
  end

(** 継承関係のパターン **)
let inheritance (human : < get_x : int ; get_y : int ; .. >) =
  print_endline ("x: " ^ string_of_int human#get_x);
  print_endline ("y: " ^ string_of_int human#get_y)

let () =
  let child = new child 0 0 in
  let stranger = new stranger 1 1 in
  inheritance child;
  (* parent classと継承関係にないにも関わらず、問題なく実行できてしまう *)
  inheritance stranger
