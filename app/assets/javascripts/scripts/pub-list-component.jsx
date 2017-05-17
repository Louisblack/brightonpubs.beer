import React from 'react';
import axios from 'axios';

class PubListComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            pubs: []
        };
    }

    render = () => {
        return <ul>
            {this.state.pubs.map(pub => {
                return <li>
                    {pub.name} - {pub.visited ? "Visited" : "Not Visited"}
                </li>
            })}
        </ul>;
    };

    componentDidMount = () => {
        axios.get('/pubs').then(response => {
            const json = response.data;
            this.setState({
                pubs: json
            }, () => console.log(this.state));
        })
    };
}

export default PubListComponent;