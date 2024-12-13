<%-- 
    Document   : penyelarasan
    Created on : Jan 20, 2010, 10:14:32 AM
    Author     : farah.shafilla
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript">
    function test(f) {
        $(f).clearForm();
    }
</script>
<s:form beanclass="etanah.view.penguatkuasaan.MesyuaratActionBean">
    <s:messages />
 <div class="subtitle">
     <c:if test="${edit}">
        <fieldset class="aras1">
            <legend>
                Keputusan Mesyuarat 
            </legend>
            <div class="content">
                
                    <p>
                        <label>Tarikh Mesyuarat :</label>
                            <s:text name="tarikhSidang" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" id=""/>
                            <font color="red" size="1">cth : hh / bb / tttt</font>&nbsp;
                    </p>
                     <p>
                    <label><font color="red">*</font>Masa :</label>
                    <s:select name="jam" style="width:61px">
                        <s:option value="0">Pilih</s:option>
                        <s:option value="01">01</s:option>
                        <s:option value="02">02</s:option>
                        <s:option value="03">03</s:option>
                        <s:option value="04">04</s:option>
                        <s:option value="05">05</s:option>
                        <s:option value="06">06</s:option>
                        <s:option value="07">07</s:option>
                        <s:option value="08">08</s:option>
                        <s:option value="09">09</s:option>
                        <s:option value="10">10</s:option>
                        <s:option value="11">11</s:option>
                        <s:option value="12">12</s:option>
                    </s:select>:
                    <s:select name="minit" style="width:61px">
                        <s:option value="0">Pilih</s:option>
                        <s:option value="00">00</s:option>
                        <s:option value="05">05</s:option>
                        <s:option value="10">10</s:option>
                        <s:option value="15">15</s:option>
                        <s:option value="20">20</s:option>
                        <s:option value="25">25</s:option>
                        <s:option value="30">30</s:option>
                        <s:option value="35">35</s:option>
                        <s:option value="40">40</s:option>
                        <s:option value="45">45</s:option>
                        <s:option value="50">50</s:option>
                        <s:option value="55">55</s:option>
                    </s:select>:
                    <s:select name="saat" style="width:61px">
                        <s:option value="0">Pilih</s:option>
                        <s:option value="00">00</s:option>
                        <s:option value="01">01</s:option>
                        <s:option value="02">02</s:option>
                        <s:option value="03">03</s:option>
                        <s:option value="04">04</s:option>
                        <s:option value="05">05</s:option>
                        <s:option value="06">06</s:option>
                        <s:option value="07">07</s:option>
                        <s:option value="08">08</s:option>
                        <s:option value="09">09</s:option>
                        <s:option value="10">10</s:option>
                        <s:option value="11">11</s:option>
                        <s:option value="12">12</s:option>
                        <s:option value="13">13</s:option>
                        <s:option value="14">14</s:option>
                        <s:option value="15">15</s:option>
                        <s:option value="16">16</s:option>
                        <s:option value="17">17</s:option>
                        <s:option value="18">18</s:option>
                        <s:option value="19">19</s:option>
                        <s:option value="20">20</s:option>
                        <s:option value="21">21</s:option>
                        <s:option value="22">22</s:option>
                        <s:option value="23">23</s:option>
                        <s:option value="24">24</s:option>
                        <s:option value="25">25</s:option>
                        <s:option value="26">26</s:option>
                        <s:option value="27">27</s:option>
                        <s:option value="28">28</s:option>
                        <s:option value="29">29</s:option>
                        <s:option value="30">30</s:option>
                        <s:option value="31">31</s:option>
                        <s:option value="32">32</s:option>
                        <s:option value="33">33</s:option>
                        <s:option value="34">34</s:option>
                        <s:option value="35">35</s:option>
                        <s:option value="36">36</s:option>
                        <s:option value="37">37</s:option>
                        <s:option value="38">38</s:option>
                        <s:option value="39">39</s:option>
                        <s:option value="40">40</s:option>
                        <s:option value="41">41</s:option>
                        <s:option value="42">42</s:option>
                        <s:option value="43">43</s:option>
                        <s:option value="44">44</s:option>
                        <s:option value="45">45</s:option>
                        <s:option value="46">46</s:option>
                        <s:option value="47">47</s:option>
                        <s:option value="48">48</s:option>
                        <s:option value="49">49</s:option>
                        <s:option value="50">50</s:option>
                        <s:option value="51">51</s:option>
                        <s:option value="52">52</s:option>
                        <s:option value="53">53</s:option>
                        <s:option value="54">54</s:option>
                        <s:option value="55">55</s:option>
                        <s:option value="56">56</s:option>
                        <s:option value="57">57</s:option>
                        <s:option value="58">58</s:option>
                        <s:option value="59">59</s:option>
                        <s:option value="60">60</s:option>
                    </s:select>
                    <s:select name="ampm" style="width:80px">
                        <s:option value="0">Pilih</s:option>
                        <s:option value="AM">AM</s:option>
                        <s:option value="PM">PM</s:option>
                    </s:select>
                </p>
                <p>
                        <label>Keputusan Mesyuarat :</label>
                       
                            <s:textarea name="permohonanRujukanLuar.catatan" rows="5" cols="50" />&nbsp;
                    </p>
            </div>
        </fieldset>
          <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
        <p align="right">
            <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
            <s:button class="btn" onclick="doSubmit(this.form, this.name, 'page_div');" name="simpan" value="Simpan"/>
        </p>
 </c:if>
        <c:if test="${view}">
 <fieldset class="aras1">
            <legend>
                Keputusan Mesyuarat
            </legend>
            <div class="content">

                <p>
                    <label>Tarikh Mesyuarat :</label>
                    <fmt:formatDate value="${actionBean.permohonanRujukanLuar.tarikhSidang}" pattern="dd/MM/yyyy" />&nbsp;
                </p>
                <p>
                    <label>Masa :</label>
                    <fmt:formatDate value="${actionBean.permohonanRujukanLuar.tarikhSidang}" pattern="hh:mm:ss aaa" />&nbsp;
                </p>
                <p>
                    <label>Keputusan Mesyuarat :</label>
                    ${actionBean.permohonanRujukanLuar.catatan}&nbsp;

                </p>
            </div>
        </fieldset>
        </c:if>
    </div>
</s:form>