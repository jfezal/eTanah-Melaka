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

<s:form beanclass="etanah.view.stripes.consent.KemasukanTarikhDanKeputusanActionBean">
    <s:errors/>
    <s:messages/>
    <s:hidden name="permohonanRujukanLuar.idPermohonan"/>
    <c:if test="${!tanahAdat}">
        <div class="subtitle">
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
                <p>
                    <label for="ID Hakmilik">ID Hakmilik :</label>
                    ${actionBean.idHakmilik}&nbsp;
                </p>
            </fieldset>
        </div>
        <br/>
    </c:if>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                <c:if test="${tanahAdat}">
                    Maklumat Bicara
                </c:if>
                <c:if test="${!tanahAdat}">
                    Maklumat Mesyuarat
                </c:if>
            </legend>

            <c:if test="${tanahAdat}">
                <c:if test="${edit}">
                    <p>
                        <label><font color="red">*</font>Tarikh Bicara :</label>
                        <s:text name="tarikhMesyuarat" id="datepicker" class="datepicker"/>
                    </p>
                    <p>
                        <label><font color="red">*</font>Masa :</label>
                        <s:select name="jam" style="width:50px">
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
                        <s:select name="minit" style="width:50px">
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
                        </s:select>
                        <s:select name="ampm" style="width:100px">
                            <s:option value="AM">Pagi</s:option>
                            <s:option value="PM">Petang</s:option>
                        </s:select>
                    </p>
                    <p>
                        <label><font color="red">*</font>Tempat Bicara :</label>
                        <s:text name="permohonanRujukanLuar.lokasiAgensi" maxlength="50" size="50" id="tempat"/>
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <s:button name="simpanTanahAdat" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
                </c:if>
                <c:if test="${!edit}">
                    <p>
                        <label>Tarikh Bicara :</label>
                        ${actionBean.tarikhMesyuarat}
                    </p>
                    <p>
                        <label>Masa :</label>
                        ${actionBean.jam}:${actionBean.minit}&nbsp;${actionBean.ampm}
                    </p>
                    <p>
                        <label>Tempat Bicara :</label>
                        ${actionBean.permohonanRujukanLuar.lokasiAgensi}
                    </p>
                </c:if>
            </c:if>

            <c:if test="${!tanahAdat}">

                <c:if test="${bilMesyuarat}">
                    <p>
                        <label><font color="red">*</font>Bilangan Mesyuarat :</label>
                        <s:text name="permohonanRujukanLuar.noSidang" maxlength="10"/>
                        <s:hidden name="permohonanRujukanLuar.idRujukan"/>
                    </p>
                </c:if>
                <c:if test="${viewBilMesyuarat}">
                    <p>
                        <label>Bilangan Mesyuarat :</label>
                        ${actionBean.permohonanRujukanLuar.noSidang}
                    </p>
                </c:if>

                <c:if test="${viewTarikhMasa}">
                    <p>
                        <label>Tarikh :</label>
                        <s:format formatPattern="dd/MM/yyyy" value="${actionBean.permohonanRujukanLuar.tarikhSidang}"/>&nbsp;
                    </p>

                </c:if>
                <c:if test="${tarikhMasa}">
                    <p>
                        <label><font color="red">*</font>Tarikh :</label>
                        <s:text name="tarikhMesyuarat" id="datepicker" class="datepicker" size="12" />
                    </p>
                </c:if>
                <c:if test="${viewTempat}">
                    <p>
                        <label>Tempat Mesyuarat :</label>
                        ${actionBean.permohonanRujukanLuar.lokasiAgensi}
                    </p>
                </c:if>
                <c:if test="${noSijil}">
                    <p>
                        <label><font color="red">*</font>Nombor Sijil :</label>
                        <s:text name="permohonanRujukanLuar.noRujukan" maxlength="30" size="40"/>
                    </p>
                </c:if>
                <c:if test="${keputusan}">
                    <p>
                        <label><font color="red">*</font>Keputusan :</label>
                        <s:radio name="keputusan" value="L"/>Lulus&nbsp;
                        <s:radio name="keputusan" value="T"/>Tolak
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
                    <c:if test="${simpanNoSijil}">
                        <s:button name="simpanNoSijil" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </c:if>
                </p>
            </c:if>
        </fieldset >
    </div>
</s:form>
