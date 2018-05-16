/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package CEPA;

import CEPA.Class.*;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.rabbitmq.RMQSink;
import org.apache.flink.streaming.connectors.rabbitmq.RMQSource;
import org.apache.flink.streaming.connectors.rabbitmq.common.RMQConnectionConfig;
import org.apache.flink.streaming.siddhi.SiddhiCEP;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;


public class StreamingJob {

	public static void main(String[] args) throws Exception {


                // set up the streaming execution environment




                final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
				//long time =15500;
            //  env.enableCheckpointing(time);

		final RMQConnectionConfig connectionConfig = new RMQConnectionConfig.Builder()
				.setUri("amqp://nizuzoyu:R-9eFbEOoHtTyPKU26fy4rjLMaZgjbgy@duckbill.rmq.cloudamqp.com/nizuzoyu")
			//	.setHost("127.0.0.1")
			//	.setUserName("guest")
			//	.setPassword("guest")
			//	.setPort(5672)
			//	.setVirtualHost("/")
				.build();


//DATAINPUT
		DataStream<InputFAHM> input=env.addSource(
				new RMQSource<String>(connectionConfig,"input_queue",true,
						new SimpleStringSchema()))//.setParallelism(1) //
				.map(new MapFunction<String,InputFAHM>() {
					@Override
					public InputFAHM map(String value)
							throws Exception {
						ObjectMapper mapper2=new ObjectMapper();
						InputData out=mapper2.readValue(value,InputData.class);
						InputFAHM da=new InputFAHM(out);
						return da;
					}
				}).name("INPUT");

		InputStream in_epi=new StreamingJob().getClass().getResourceAsStream("/EpilepticAttackExPlan");
		BufferedReader reader_epi = new BufferedReader(new InputStreamReader(in_epi));
		StringBuilder sb_epi = new StringBuilder();
		for (String line; ( line = reader_epi.readLine()) != null;) {
			sb_epi.append(line+"\n");
		}
		InputStream in_hea=new StreamingJob().getClass().getResourceAsStream("/HeartAttackExPlan");
		BufferedReader reader_hea = new BufferedReader(new InputStreamReader(in_hea));
		StringBuilder sb_hea = new StringBuilder();
		for (String line; ( line = reader_hea.readLine()) != null;) {
			sb_hea.append(line+"\n");
		}
		InputStream in_hyp=new StreamingJob().getClass().getResourceAsStream("/HypoxicAttackExPlan");
		BufferedReader reader_hyp = new BufferedReader(new InputStreamReader(in_hyp));
		StringBuilder sb_hyp = new StringBuilder();
		for (String line; ( line = reader_hyp.readLine()) != null;) {
			sb_hyp.append(line+"\n");
		}



		String EpilepticAttack=sb_epi.toString();

		String HeartAttack=sb_hea.toString();
		String HypoxicAttack=sb_hyp.toString();

		DataStream<InputFAHM> id_input=input.keyBy("meta_id_utente");

//QUERYES

		DataStream<Map<String,Object>> output_epi=SiddhiCEP.define("InputStream",id_input,    "meta_id_utente","meta_id_doctor","meta_alertContact","meta_timestamp","meta_routingKey","epileptic_subject",
				"recent_surgery","systolic_pressure","diastolic_pressure","heart_rate","movement_type",
				"epilettiform_pattern","perc_sat_hemoglobin","hypertensive_patient","hypotensive_patient")
				.cql(EpilepticAttack)
				.returnAsMap("EpilepticAttackAlert");

		DataStream<Map<String,Object>> output_hyp=SiddhiCEP.define("InputStream",id_input,    "meta_id_utente","meta_id_doctor","meta_alertContact","meta_timestamp","meta_routingKey","epileptic_subject",
				"recent_surgery","systolic_pressure","diastolic_pressure","heart_rate","movement_type",
				"epilettiform_pattern","perc_sat_hemoglobin","hypertensive_patient","hypotensive_patient")
				.cql(HypoxicAttack)
				.returnAsMap("HypoxicStream");
		DataStream<Map<String,Object>> output_hea=SiddhiCEP.define("InputStream",id_input,    "meta_id_utente","meta_id_doctor","meta_alertContact","meta_timestamp","meta_routingKey","epileptic_subject",
				"recent_surgery","systolic_pressure","diastolic_pressure","heart_rate","movement_type",
				"epilettiform_pattern","perc_sat_hemoglobin","hypertensive_patient","hypotensive_patient")
				.cql(HeartAttack)
				.returnAsMap("HeartBrake_AttackAlert");
//MAPPINGOUTPUT
		DataStream<String> sink_hyp = output_hyp.map(new MapFunction<Map<String, Object>, String>() {
			@Override
			public String map(Map<String, Object> map) throws Exception {
				ObjectMapper mapper2 = new ObjectMapper();

				BaseOutput base= mapper2.convertValue(map, BaseOutput.class);
				Hypoxic hyp=new Hypoxic(base);
				String string = mapper2.writeValueAsString(hyp);
				return string;

			}
		}).name("QueryMapping");

		DataStream<String> sink_hea = output_hea.map(new MapFunction<Map<String, Object>, String>() {
			@Override
			public String map(Map<String, Object> map) throws Exception {
				ObjectMapper mapper2 = new ObjectMapper();

				BaseOutput base= mapper2.convertValue(map, BaseOutput.class);
				HeartBreak hea=new HeartBreak(base);
				String string = mapper2.writeValueAsString(hea);
				return string;

			}
		}).name("QueryMapping");

		DataStream<String> sink_epi = output_epi.map(new MapFunction<Map<String, Object>, String>() {
			@Override
			public String map(Map<String, Object> map) throws Exception {
				ObjectMapper mapper2 = new ObjectMapper();

				BaseOutput base= mapper2.convertValue(map, BaseOutput.class);
				Epileptic epi=new Epileptic(base);
				String string = mapper2.writeValueAsString(epi);
				return string;

			}
		}).name("QueryMapping");

		sink_hyp.print();
		sink_epi.print();
		sink_hea.print();//debugging

		//SINK
		sink_hyp.addSink(new RMQSink<String>(
				connectionConfig,            // config for the RabbitMQ connection
				"output_queue",
				// name of the RabbitMQ queue to send messages to
				new SimpleStringSchema())).name("HypoxicAttack");  // serialization schema to turn Java objects to messages


		sink_epi.addSink(new RMQSink<String>(
				connectionConfig,            // config for the RabbitMQ connection
				"output_queue",
				// name of the RabbitMQ queue to send messages to
				new SimpleStringSchema())).name("EpilepticAttack");  // serialization schema to turn Java objects to messages


		sink_hea.addSink(new RMQSink<String>(
				connectionConfig,            // config for the RabbitMQ connection
				"output_queue",
				// name of the RabbitMQ queue to send messages to
				new SimpleStringSchema())).name("HeartAttack");  // serialization schema to turn Java objects to messages







		env.execute("FAHM ");
	}
}
