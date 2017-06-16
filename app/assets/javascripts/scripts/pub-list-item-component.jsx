import React from "react";
import {Link} from 'react-router-dom'
import LazyLoad from 'react-lazyload';

import axios from "axios";


class PubListItemComponent extends React.Component {

    render = () => {
        return <li key={this.props.pub.id} className="pub-list__list__item">
            <div className="container-fluid">
                <div className="row">
                    {this.status()}
                    <h3 className=".col-md-12"><Link to={`/pub/${this.props.pub.id}`}>{this.props.pub.name}</Link></h3>
                </div>
                <div className="row">
                    <LazyLoad offset={400}>
                        {this.props.pub.imgUrl ? <img src={this.props.pub.imgUrl} className="pub-list__list__item__image .col-md-12"/> : null}
                    </LazyLoad>
                </div>
            </div>
        </li>;
    };

    visitPub = (id) => {
        if (!this.props.pub.visited) {
            axios.post(`/pubs/visit/${id}`)
                .then(this.props.refresh);
        } else {
            axios.post(`/pubs/unvisit/${id}`)
                .then(this.props.refresh);
        }
    };

    status = () => {
        if (this.props.loggedIn) {
            let className = "pub-list__list__item__status--" + (this.props.pub.visited ? "visited" : "not-visited");
            return <div className={className + " glyphicon glyphicon-ok-circle pub-list__list__item__status"}
                        onClick={e => this.visitPub(this.props.pub.id)}></div>
        }

    }

}

export default PubListItemComponent;