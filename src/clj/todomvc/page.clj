(ns todomvc.page
  (:require [om.next :as om]
            [om.dom :as dom]
            [hiccup.page :as hiccup]
            [todomvc.todomvc :as td]))

(defn render-page [{:keys [datomic-connection] :as req}]
  (let [r (td/make-reconciler datomic-connection)
        c (om/add-root! r td/Todos nil)
        html-string (dom/render-to-str c)]
    (hiccup/html5
      [:head
       [:meta {:charset "utf-8"}]
       [:meta {:http-equiv "X-UA-Compatible"
               :content "IE=edge"}]
       [:title "Om TodoMVC"]
       (hiccup/include-css "/bower_components/todomvc-common/base.css")]
      [:body
       [:section#todoapp html-string]
       [:footer#info]
       [:div#benchmark]
       (hiccup/include-js "/bower_components/todomvc-common/base.js")
       (hiccup/include-js "/js/app.js")])))
