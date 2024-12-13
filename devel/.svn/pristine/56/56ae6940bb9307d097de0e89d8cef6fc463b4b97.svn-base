<%-- 
    Document   : kemasukan_tarikh_dan_keputusan
    Created on : May 16, 2010, 12:31:56 PM
    Author     : sitifariza.hanim
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252" language="java"%>
<link type="text/css" href="${pageContext.request.contextPath}/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui.datetimepicker.js"></script>
<script type="text/javascript">

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }

    function removeNonNumeric( strString )
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for( intIndex = 0; intIndex < strString.length; intIndex++ )
        {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }

</script>

<s:form beanclass="etanah.view.stripes.pelupusan.KemasukanTarikhDanKeputusanActionBean">

    <div class="subtitle">
        <s:errors/>
        <s:messages/>
        <s:hidden name="permohonanRujukanLuar.idPermohonan"/>
        <fieldset class="aras1">
            <legend>Maklumat Permohonan</legend>
            <p>
                <label for="ID Permohonan">ID Permohonan / Perserahan :</label>
                ${actionBean.permohonan.idPermohonan}&nbsp;
            </p>
            <p>
                <label for="Permohonan">Urusan :</label>
                ${actionBean.permohonan.kodUrusan.nama}&nbsp;
            </p>
        </fieldset >
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Mesyuarat</legend>
            <c:if test="${bilMesyuarat}">
                <p>
                    <label><font color="red">*</font>Bilangan Mesyuarat :</label>
                    <s:text name="permohonanRujukanLuar.noSidang" onkeyup="validateNumber(this,this.value);" maxlength="3"/>
                    <s:hidden name="permohonanRujukanLuar.idRujukan"/>
                </p>
            </c:if>

            <c:if test="${displayTarikhMasa}">
                <p>
                    <label>Tarikh :</label>
                    ${actionBean.tarikhMesyuarat}&nbsp;
                </p>
                <p>
                    <label>Masa :</label>
                    ${actionBean.jam}:${actionBean.minit}:${actionBean.saat}&nbsp;${actionBean.ampmDisplay}&nbsp;
                </p>
            </c:if>
            <c:if test="${tarikhMasa}">
                <p>
                    <label><font color="red">*</font>Tarikh :</label>
                    <s:text name="tarikhMesyuarat" id="datepicker" class="datepicker" />
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
                        <s:option value="AM">Pagi</s:option>
                        <s:option value="PM">Petang</s:option>
                    </s:select>
                </p>
            </c:if>
            <c:if test="${keputusan}">
                <p>
                    <label><font color="red">*</font>Keputusan :</label>
                    <s:radio name="keputusan" value="L"/>Lulus&nbsp;
                    <s:radio name="keputusan" value="T"/>Tolak&nbsp;
                    <s:radio name="keputusan" value="L"/>Tangguh
                </p>
            </c:if>
            <c:if test="${ulasan}">
                <p>
                    <label>Ulasan :</label>
                    <s:textarea name="permohonanRujukanLuar.ulasan" cols="50" rows="5"/>
                </p>
            </c:if>
            <p>
                <label>&nbsp;</label>
                <c:if test="${simpanUlasan2}">
                    <s:button name="simpanUlasan2" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </c:if>
                <c:if test="${simpanUlasan}">
                    <s:button name="simpanUlasan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </c:if>
                <c:if test="${simpanDate}">
                    <s:button name="simpanDate" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </c:if>
                <c:if test="${simpanTiadaResult}">
                    <s:button name="simpanTiadaResult" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </c:if>
                <c:if test="${simpanMesyuarat}">
                    <s:button name="simpanMesyuarat" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </c:if>
            </p>
        </fieldset >
    </div>
</s:form>
