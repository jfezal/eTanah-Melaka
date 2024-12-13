<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252" language="java"%>
<link type="text/css" href="${pageContext.request.contextPath}/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui.datetimepicker.js"></script>
<script type="text/javascript">

    $(document).ready(function(){
        var val = $('#denda').val();
        if(val != ''){
            convert(val, 'denda');
        }
        var val2 = $('#premium').val();
        if(val2 != ''){
            convert(val2, 'premium');
        }
    });
    
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

    function reloadEdit (val) {
        doBlockUI();
        var url = '${pageContext.request.contextPath}/consent/maklumat_bayaran?reloadEdit&idHakmilik=' + val;
        $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            error : function (xhr, ajaxOptions, thrownError){
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success : function(data){
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });

    }

    function reload (val) {
        doBlockUI();
        var url = '${pageContext.request.contextPath}/consent/maklumat_bayaran?reload&idHakmilik=' + val;
        $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            error : function (xhr, ajaxOptions, thrownError){
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success : function(data){
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });

    }

</script>

<s:form beanclass="etanah.view.stripes.consent.MaklumatBayaranActionBean">
    <s:errors/>
    <s:messages/>
    <fieldset class="aras1">
        <legend>Senarai Hakmilik Terlibat</legend>
        <div align="center">
            <c:if test="${fn:length(actionBean.senaraiHakmilikTerlibat) > 1}">
                <p>
                    <font size="2" color="red">
                    <b>Permohonan Melibatkan Banyak Hakmilik</b>
                    </font>
                </p>
            </c:if>
        </div>
        <div align="center">          
            <font color="#003194" style="font-weight: bold; font-family:Tahoma; font-size: 13px;">
            Hakmilik :
            </font>
            <c:if test="${edit}">
                <s:select name="idHakmilik" onchange="reloadEdit(this.value);" id="hakmilik">
                    <c:forEach items="${actionBean.senaraiHakmilikTerlibat}" var="item" varStatus="line">
                        <s:option value="${item.hakmilik.idHakmilik}" style="width:400px">
                            ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
                        </s:option>
                    </c:forEach>
                </s:select>
            </c:if>
            <c:if test="${!edit}">
                <s:select name="idHakmilik" onchange="reload(this.value);" id="hakmilik">
                    <c:forEach items="${actionBean.senaraiHakmilikTerlibat}" var="item" varStatus="line">
                        <s:option value="${item.hakmilik.idHakmilik}" style="width:400px">
                            ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
                        </s:option>
                    </c:forEach>
                </s:select>
            </c:if>
        </div>
        <br/>
    </fieldset>
    <br/>
    <c:if test="${actionBean.fasaPermohonan ne null }">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Bayaran Asal
                </legend>
                <p>
                    <label>Yuran Notis Kelulusan (RM) :</label>
                    <c:if test="${actionBean.permohonanTuntutanKosAsal.amaunTuntutan ne null}"><font style="text-transform:uppercase;">  ${actionBean.permohonanTuntutanKosAsal.amaunTuntutan}</font> </c:if>
                    <c:if test="${actionBean.permohonanTuntutanKosAsal.amaunTuntutan eq null}"> TIADA DATA </c:if>
                </p>

                <p>
                    <label>Sumbangan Tabung Amanah - Lebih Keluasan (RM) :</label>
                    <c:if test="${actionBean.permohonanTuntutanKosAsal2.amaunTuntutan ne null}"><font style="text-transform:uppercase;">  ${actionBean.permohonanTuntutanKosAsal2.amaunTuntutan}</font> </c:if>
                    <c:if test="${actionBean.permohonanTuntutanKosAsal2.amaunTuntutan eq null}"> TIADA DATA </c:if>
                </p>

                <p>
                    <label>Sumbangan Tabung Amanah - Lebih Unit Maksima (RM) :</label>
                    <c:if test="${actionBean.permohonanTuntutanKosAsal3.amaunTuntutan ne null}"><font style="text-transform:uppercase;">  ${actionBean.permohonanTuntutanKosAsal3.amaunTuntutan}</font> </c:if>
                    <c:if test="${actionBean.permohonanTuntutanKosAsal3.amaunTuntutan eq null}"> TIADA DATA </c:if>
                </p>
                <br/>
            </fieldset>
        </div>
        <br/>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Bayaran
                </legend>
                <c:if test="${actionBean.stageId ne 'Stage8'}">
                    <p>
                        <label><font color="red">*</font>Yuran Notis Kelulusan (RM) :</label>
                            <s:text name="permohonanTuntutanKos.amaunTuntutan" size="31" id="premium" onchange="convert(this.value, this.id);"
                                    formatPattern="#,###.00" onkeyup="validateNumber(this,this.value);" />
                    </p>

                    <%--                  <p>
                                <label>Sumbangan Tabung Amanah Rumah Kos Rendah (RM) :</label>
                                <s:text name="permohonanTuntutanKos2.amaunTuntutan" size="31" id="denda" onchange="convert(this.value, this.id);"
                                        formatPattern="#,###.00" onkeyup="validateNumber(this,this.value);"/>
                            </p>--%>

                    <p>
                        <label> Sumbangan Tabung Amanah - Lebih Keluasan (RM) :</label>
                        <s:text name="permohonanTuntutanKos2.amaunTuntutan" size="31" id="denda1" onchange="convert(this.value, this.id);"
                                formatPattern="#,###.00" onkeyup="validateNumber(this,this.value);"/>
                    </p>
                    <p>
                        <label> Sumbangan Tabung Amanah - Lebih Unit Maksima (RM) :</label>
                        <s:text name="permohonanTuntutanKos3.amaunTuntutan" size="31" id="denda2" onchange="convert(this.value, this.id);"
                                formatPattern="#,###.00" onkeyup="validateNumber(this,this.value);"/>
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
                </c:if>
                <c:if test="${actionBean.stageId eq 'Stage8'}">
                    <p>
                        <label>Yuran Notis Kelulusan (RM) :</label>
                        <c:if test="${actionBean.permohonanTuntutanKos.amaunTuntutan ne null}"><font style="text-transform:uppercase;">  ${actionBean.permohonanTuntutanKos.amaunTuntutan}</font> </c:if>
                        <c:if test="${actionBean.permohonanTuntutanKos.amaunTuntutan eq null}"> TIADA DATA </c:if>
                    </p>

                    <p>
                        <label>Sumbangan Tabung Amanah - Lebih Keluasan (RM) :</label>
                        <c:if test="${actionBean.permohonanTuntutanKos2.amaunTuntutan ne null}"><font style="text-transform:uppercase;">  ${actionBean.permohonanTuntutanKos2.amaunTuntutan}</font> </c:if>
                        <c:if test="${actionBean.permohonanTuntutanKos2.amaunTuntutan eq null}"> TIADA DATA </c:if>
                    </p>

                    <p>
                        <label>Sumbangan Tabung Amanah - Lebih Unit Maksima (RM) :</label>
                        <c:if test="${actionBean.permohonanTuntutanKos3.amaunTuntutan ne null}"><font style="text-transform:uppercase;">  ${actionBean.permohonanTuntutanKos3.amaunTuntutan}</font> </c:if>
                        <c:if test="${actionBean.permohonanTuntutanKos3.amaunTuntutan eq null}"> TIADA DATA </c:if>
                    </p>
                </c:if>
                <br/>
            </fieldset>
        </div>
        <br/>
    </c:if>
    <c:if test="${actionBean.fasaPermohonan eq null}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Bayaran
                </legend>
                <c:if test="${edit}">

                    <p>
                        <label><font color="red">*</font>Yuran Notis Kelulusan (RM) :</label>
                            <s:text name="permohonanTuntutanKos.amaunTuntutan" size="31" id="premium" onchange="convert(this.value, this.id);"
                                    formatPattern="#,###.00" onkeyup="validateNumber(this,this.value);" />
                    </p>
                    <!--                    <p>
                                            <label>Sumbangan Tabung Amanah Rumah Kos Rendah :</label>&nbsp;
                                        </p>-->

                    <p>
                        <label> Sumbangan Tabung Amanah - Lebih Keluasan (RM) :</label>
                        <s:text name="permohonanTuntutanKos2.amaunTuntutan" size="31" id="denda1" onchange="convert(this.value, this.id);"
                                formatPattern="#,###.00" onkeyup="validateNumber(this,this.value);"/>
                    </p>
                    <p>
                        <label> Sumbangan Tabung Amanah - Lebih Unit Maksima (RM) :</label>
                        <s:text name="permohonanTuntutanKos3.amaunTuntutan" size="31" id="denda2" onchange="convert(this.value, this.id);"
                                formatPattern="#,###.00" onkeyup="validateNumber(this,this.value);"/>
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
                </c:if>
                <c:if test="${!edit}">
                    <p>
                        <label>Yuran Notis Kelulusan (RM) :</label>
                        <c:if test="${actionBean.permohonanTuntutanKos.amaunTuntutan ne null}"><font style="text-transform:uppercase;">  ${actionBean.permohonanTuntutanKos.amaunTuntutan}</font> </c:if>
                        <c:if test="${actionBean.permohonanTuntutanKos.amaunTuntutan eq null}"> TIADA DATA </c:if>
                    </p>

                    <p>
                        <label>Sumbangan Tabung Amanah - Lebih Keluasan (RM) :</label>
                        <c:if test="${actionBean.permohonanTuntutanKos2.amaunTuntutan ne null}"><font style="text-transform:uppercase;">  ${actionBean.permohonanTuntutanKos2.amaunTuntutan}</font> </c:if>
                        <c:if test="${actionBean.permohonanTuntutanKos2.amaunTuntutan eq null}"> TIADA DATA </c:if>
                    </p>
                    <p>
                        <label>Sumbangan Tabung Amanah - Lebih Unit Maksima (RM) :</label>
                        <c:if test="${actionBean.permohonanTuntutanKos3.amaunTuntutan ne null}"><font style="text-transform:uppercase;">  ${actionBean.permohonanTuntutanKos3.amaunTuntutan}</font> </c:if>
                        <c:if test="${actionBean.permohonanTuntutanKos3.amaunTuntutan eq null}"> TIADA DATA </c:if>
                    </p>
                    <br/>
                </c:if>
            </fieldset>
        </div>
    </c:if>
</s:form>
