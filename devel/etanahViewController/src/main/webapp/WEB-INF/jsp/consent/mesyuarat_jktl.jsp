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


    function dateValidation(id,value){
        var today = new Date();
        var dayToday = today.getDate();
        var monthToday = today.getMonth() + 1;
        var yearToday = today.getFullYear()
        var day = value.substring(0,2);
        var month = value.substring(3,5);
        var year = value.substring(6,10);

        if(year < yearToday ){
            alert("Tarikh yang dimasukkan tidak sesuai.");
            $('#'+id).val("");
        }
        else if(month < monthToday && year < yearToday){
            alert("Tarikh yang dimasukkan tidak sesuai.");
            $('#'+id).val("");
            
        }
        else if(day < dayToday && month <= monthToday && year <= yearToday){
            alert("Tarikh yang dimasukkan tidak sesuai.");
            $('#'+id).val("");            
        }
    }


</script>

<s:form beanclass="etanah.view.stripes.consent.MesyuaratJktlActionBean">
    <s:errors/>
    <s:messages/>
    <s:hidden name="permohonanRujukanLuar.idRujukan"/>
    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PJKTL'}">
        <fieldset class="aras1">
            <legend>Maklumat Mesyuarat</legend>
            <c:if test="${edit}">
                <p>
                    <label><font color="red">*</font>Tarikh Mesyuarat :</label>
                    <s:text name="tarikhMesyuarat" id="datepicker" size="31" class="datepicker" onchange="dateValidation(this.id, this.value)"/>
                </p>
                <p>
                    <label><font color="red">*</font>Masa Mesyuarat :</label>
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
                    <label><font color="red">*</font>Tempat Mesyuarat :</label>
                    <s:textarea name="permohonanRujukanLuar.lokasiAgensi" rows="3"  cols="32" id="tempat"/>
                </p>
                <p>
                    <label><font color="red">*</font>Nombor Kertas Mesyuarat :</label>
                    <s:text name="permohonanRujukanLuar.noSidang"  maxlength="27" size="31" id="bilSidang"/>
                    <s:hidden name="permohonanRujukanLuar.idRujukan"/>
                </p>

                <p>
                    <label>&nbsp;</label>
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
            </c:if>
            <c:if test="${!edit}">
                <p>
                    <label>Tarikh Mesyuarat :</label>
                    ${actionBean.tarikhMesyuarat}&nbsp;
                </p>
                <p>
                    <label>Masa Mesyuarat :</label>
                    ${actionBean.jam}:${actionBean.minit}&nbsp;${actionBean.ampm}
                </p>
                <p>
                    <label>Tempat Mesyuarat :</label>
                    ${actionBean.permohonanRujukanLuar.lokasiAgensi}&nbsp;
                </p>
                <p>
                    <label>Nombor Kertas Mesyuarat :</label>
                    ${actionBean.permohonanRujukanLuar.noSidang}&nbsp;
                </p>
            </c:if>
            <br/>
        </fieldset>

    </c:if>
    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PJKTL'}">
        <c:if test="${fn:length(actionBean.listMesyuaratTangguh) > 0}">
            <fieldset class="aras1">
                <legend>
                    Maklumat Mesyuarat MMK Terdahulu
                </legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.listMesyuaratTangguh}" cellpadding="0" cellspacing="0" id="line"
                                   requestURI="${pageContext.request.contextPath}/consent/maklumat_mesyuarat">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="Tarikh">
                            <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhSidang}"/>
                        </display:column>
                        <display:column property="noSidang" title="Nombor Kertas Mesyuarat" />
                        <display:column title="Status">
                            TANGGUH
                        </display:column>
                    </display:table>
                </div>
            </fieldset>
            <br/>
        </c:if>

        <c:if test="${actionBean.stageId eq 'Stage6' || actionBean.stageId eq 'Stage7'}">
            <fieldset class="aras1">
                <legend>Maklumat Mesyuarat</legend>
                <c:if test="${edit}">
                    <p>
                        <label><font color="red">*</font>Tarikh Mesyuarat :</label>
                        <s:text name="tarikhMesyuarat" id="datepicker" size="31" class="datepicker" onchange="dateValidation(this.id, this.value)"/>
                    </p>
                    <p>
                        <label><font color="red">*</font>Masa Mesyuarat :</label>
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
                        <label><font color="red">*</font>Tempat Mesyuarat :</label>
                        <s:textarea name="permohonanRujukanLuar.lokasiAgensi" rows="3"  cols="32" id="tempat"/>
                    </p>
                    <p>
                        <label><font color="red">*</font>Nombor Kertas Mesyuarat :</label>
                        <s:text name="permohonanRujukanLuar.noSidang" maxlength="27" size="31" id="bilSidang"/>
                        <s:hidden name="permohonanRujukanLuar.idRujukan"/>
                    </p>

                    <p>
                        <label>&nbsp;</label>
                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
                </c:if>
                <c:if test="${!edit}">
                    <p>
                        <label>Tarikh Mesyuarat :</label>
                        ${actionBean.tarikhMesyuarat}&nbsp;
                    </p>
                    <p>
                        <label>Masa Mesyuarat :</label>
                        ${actionBean.jam}:${actionBean.minit}&nbsp;${actionBean.ampm}
                    </p>
                    <p>
                        <label>Tempat Mesyuarat :</label>
                        ${actionBean.permohonanRujukanLuar.lokasiAgensi}&nbsp;
                    </p>
                    <p>
                        <label>Nombor Kertas Mesyuarat :</label>
                        ${actionBean.permohonanRujukanLuar.noSidang}&nbsp;
                    </p>
                </c:if>
                <br/>
            </fieldset>
        </c:if>
        <c:if test="${actionBean.stageId ne 'Stage6' && actionBean.stageId ne 'Stage7'}">
            <fieldset class="aras1">
                <legend>
                    Maklumat Mesyuarat MMK
                </legend>
                <c:if test="${edit}">
                    <p>
                        <label><font color="red">*</font>Tarikh Mesyuarat :</label>
                        <s:text name="tarikhMesyuarat" id="datepicker" class="datepicker" onchange="dateValidation(this.id, this.value)"/>
                    </p>
                    <p>
                        <label><font color="red">*</font>Nombor Kertas Mesyuarat :</label>
                        <s:text name="permohonanRujukanLuar.noSidang" id="noSidang"  maxlength="27"/>
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <s:button name="simpanMmk" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>

                </c:if>
                <c:if test="${!edit}">
                    <p>
                        <label>Tarikh Mesyuarat :</label>
                        ${actionBean.tarikhMesyuarat}&nbsp;
                    </p>
                    <p>
                        <label>Nombor Kertas Mesyuarat :</label>
                        ${actionBean.permohonanRujukanLuar.noSidang}&nbsp;
                    </p>
                </c:if>
                <br/>
            </fieldset>
        </c:if>
    </c:if>
</s:form>
