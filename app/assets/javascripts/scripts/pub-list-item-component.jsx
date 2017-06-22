import React from "react";
import {Link} from 'react-router-dom'
import LazyLoad from 'react-lazyload';

import Status from './pub-visit-status.jsx';


class PubListItemComponent extends React.Component {

    render = () => {
        return <li key={this.props.pub.id} className="pub-list__list__item">
            <div className="container-fluid">
                <div className="row">
                    <Status pub={this.props.pub} refresh={this.props.refresh} loggedIn={this.props.loggedIn} />
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

}

export default PubListItemComponent;