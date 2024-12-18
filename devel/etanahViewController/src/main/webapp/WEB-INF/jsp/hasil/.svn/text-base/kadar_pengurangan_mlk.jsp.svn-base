<%-- 
    Document   : kadar_pengurangan_mlk
    Created on : Feb 17, 2010, 10:49:36 AM
    Author     : abu.mansur
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    $(document).ready(function() {
                var kodUrusan = '${actionBean.permohonan.kodUrusan.kod}';
                var v1 = '${actionBean.permohonan.nilaiKeputusan}';
                var v = null;
                var result=null;
                var vrpppSB = '${actionBean.akaun.baki}';
                if(kodUrusan != 'RPPP'){
                    if(kodUrusan == 'PPDL' || kodUrusan == 'PPPT')
                        v = '${actionBean.denda}';
                    if(kodUrusan == 'PRCT')
                        v = '${actionBean.akaun.baki}';<%--v = '${actionBean.hakmilik.cukaiSebenar}';--%>
                    v1 = v * v1;
                    result=Math.round(v1*10)/10;
                    $('#jumlah').val(result.toFixed(2)); //BigDecimal.Round_Down
                    $('#denda').val((v - result).toFixed(2));
                }else{
                    var vrppp = Math.round(vrpppSB*10)/10;
                    result = (vrppp * v1)+vrppp;
                    $('#sblmGanda').val(result.toFixed(2)); //BigDecimal.Round_Down
                    $('#slpsGanda').val(result.toFixed(2));
                }
            });

            function changePercentage(value){
                var kodUrusan = '${actionBean.permohonan.kodUrusan.kod}';
                var v = null;
                var result=null;
                if(kodUrusan == 'PPDL' || kodUrusan == 'PPPT'){
                    v = '${actionBean.denda}';
                }else if(kodUrusan == 'PRCT'){
                    v = '${actionBean.akaun.baki}';<%--v = '${actionBean.hakmilik.cukaiSebenar}';--%>
                }
                var v1 = 0;
                if(value=="0.1"){
                    v1 = v * 0.1;
                    result=Math.round(v1*10)/10;
                    $('#jumlah').val(result.toFixed(2));
                    $('#denda').val((v - result).toFixed(2));
                }
                if(value=="0.2"){
                    v1 = v * 0.2;
                    result=Math.round(v1*10)/10;
                    $('#jumlah').val(result.toFixed(2));
                    $('#denda').val((v - result).toFixed(2));
                }
                if(value=="0.3"){
                    v1 = v * 0.3;
                    result=Math.round(v1*10)/10;
                    $('#jumlah').val(result.toFixed(2)); 
                    $('#denda').val((v - result).toFixed(2));
                }
                if(value=="0.4"){
                    v1 = v * 0.4;
                    result=Math.round(v1*10)/10;
                    $('#jumlah').val(result.toFixed(2)); 
                    $('#denda').val((v - result).toFixed(2));
                }
                if(value=="0.5"){
                    v1 = v * 0.5;
                    result=Math.round(v1*10)/10;
                    $('#jumlah').val(result.toFixed(2)); 
                    $('#denda').val((v - result).toFixed(2));
                }
                if(value=="0.6"){
                    v1 = v * 0.6;
                    result=Math.round(v1*10)/10;
                    $('#jumlah').val(result.toFixed(2)); 
                    $('#denda').val((v - result).toFixed(2));
                }
                if(value=="0.7"){
                    v1 = v * 0.7;
                    result=Math.round(v1*10)/10;
                    $('#jumlah').val(result.toFixed(2)); 
                    $('#denda').val((v - result).toFixed(2));
                }
                if(value=="0.8"){
                    v1 = v * 0.8;
                    result=Math.round(v1*10)/10;
                    $('#jumlah').val(result.toFixed(2)); 
                    $('#denda').val((v - result).toFixed(2));
                }
                if(value=="0.9"){
                    v1 = v * 0.9;
                    result=Math.round(v1*10)/10;
                    $('#jumlah').val(result.toFixed(2)); 
                    $('#denda').val((v - result).toFixed(2));
                }
                if(value=="1.0"){
                    v1 = v * 1.0;
                    result=Math.round(v1*10)/10;
                    $('#jumlah').val(result.toFixed(2)); 
                    $('#denda').val((v - result).toFixed(2));
                }
            }

            function amaunSlps(value){
                var gandaan = parseInt(value);
                var vSB = '${actionBean.akaun.baki}';
                var v = Math.round(vSB*10)/10;
                var v1 = 0;
                var result=null;
                switch(gandaan){
                    case 1 : v1 = (v * gandaan)+v;
                        break;
                    case 2 : v1 = (v * gandaan)+v;
                        break;
                    case 3 : v1 = (v * gandaan)+v;
                        break;
                    case 4 : v1 = (v * gandaan)+v;
                        break;
                    case 5 : v1 = (v * gandaan)+v;
                        break;
                    default : v1 = 00000;
                }
                result= Math.round(v1*10)/10;
                <%--alert('value slps gandaan : RM '+v1);--%>
                $('#sblmGanda').val(v.toFixed(2));
                $('#slpsGanda').val(result.toFixed(2));
            }
            
            function reloadMakLumatCukai(id){
                alert(id);
                doBlockUI();
                var url = '${pageContext.request.contextPath}/hasil/kadar_pengurangan_mlk?reloadEdit&idHakmilik=' + id;
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
<s:form beanclass="etanah.view.stripes.hasil.KadarPenguranganMlkActionBean">
    <div class="subtitle">
            <fieldset class="aras1">
                <legend>Senarai Hakmilik Terlibat</legend>
                <div align="center">          
                    <font color="#003194" style="font-weight: bold; font-family:Tahoma; font-size: 13px;">Hakmilik :</font>
                    <s:select name="idHakmilik" onchange="reloadMakLumatCukai(this.value);" id="hakmilik">
                        <c:forEach items="${actionBean.senaraiHakmilikTerlibat}" var="item" varStatus="line">
                            <s:option value="${item.hakmilik.idHakmilik}" style="width:450px">
                                ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
                            </s:option>
                        </c:forEach>
                    </s:select>
                </div>
                <br/>
            </fieldset>
            <br/>
        </div>
    <div class="subtitle">
        <br>
        <fieldset class="aras1">

            <p>
                <s:messages />
                <s:errors />&nbsp;
            </p>
            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'RPPP'}">
                <p>
                    <label>Kadar :</label>
                    <s:radio name="permohonan.nilaiKeputusan" value="0.1"  onclick="javaScript:changePercentage(this.value)"/> 10%
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:radio name="permohonan.nilaiKeputusan" value="0.2" onclick="javaScript:changePercentage(this.value)"/> 20%
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:radio name="permohonan.nilaiKeputusan" value="0.3" onclick="javaScript:changePercentage(this.value)"/> 30%
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:radio name="permohonan.nilaiKeputusan" value="0.4" onclick="javaScript:changePercentage(this.value)"/> 40%
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:radio name="permohonan.nilaiKeputusan" value="0.5" onclick="javaScript:changePercentage(this.value)"/> 50%
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:radio name="permohonan.nilaiKeputusan" value="0.6" onclick="javaScript:changePercentage(this.value)"/> 60%
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:radio name="permohonan.nilaiKeputusan" value="0.7" onclick="javaScript:changePercentage(this.value)"/> 70%
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:radio name="permohonan.nilaiKeputusan" value="0.8" onclick="javaScript:changePercentage(this.value)"/> 80%
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:radio name="permohonan.nilaiKeputusan" value="0.9" onclick="javaScript:changePercentage(this.value)"/> 90%
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:radio name="permohonan.nilaiKeputusan" value="1.0" onclick="javaScript:changePercentage(this.value)"/> 100%
                </p>
                <p>
                    <label>Amaun Kadar Pengurangan (RM) :</label>
                    <s:text name="xxx" readonly="true" style="text-align:right;" id="jumlah" size="15"/>
                </p>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPDL'}">
                    <p>
                        <label>Amaun Denda Semasa (RM) :</label>
                        <s:text name="xxx" readonly="true" style="text-align:right;" id="denda" size="15"/>
                    </p>
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRCT' or actionBean.permohonan.kodUrusan.kod eq 'PPPT'}">
                    <p>
                        <label>Amaun Selepas Pengurangan (RM) :</label>
                        <s:text name="xxx" disabled="true" style="text-align:right;" id="denda" size="15"/>
                    </p>
                </c:if>
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'RPPP'}">
                <p>
                    <label>Kadar Gandaan :</label>
                    <s:radio name="permohonan.nilaiKeputusan" value="1" onclick="javaScript:amaunSlps(this.value)"/> 1 Kali Ganda
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:radio name="permohonan.nilaiKeputusan" value="2" onclick="javaScript:amaunSlps(this.value)"/> 2 Kali Ganda
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:radio name="permohonan.nilaiKeputusan" value="3" onclick="javaScript:amaunSlps(this.value)"/> 3 Kali Ganda
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:radio name="permohonan.nilaiKeputusan" value="4" onclick="javaScript:amaunSlps(this.value)"/> 4 Kali Ganda
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:radio name="permohonan.nilaiKeputusan" value="5" onclick="javaScript:amaunSlps(this.value)"/> 5 Kali Ganda
                </p>
                <p>
                    <label>Amaun Sebelum Gandaan (RM) :</label>
                    <s:text name="xxx" disabled="true" style="text-align:right;" id="sblmGanda" size="15"/>
                </p>
                <p>
                    <label>Amaun Selepas Gandaan (RM) :</label>
                    <s:text name="xxx" disabled="true" style="text-align:right;" id="slpsGanda" size="15"/>
                </p>
            </c:if>
            <p align="right">
                <s:button class="btn" onclick="doSubmit(this.form, this.name, 'page_div');" name="saveOrUpdate" value="Simpan"/>&nbsp;
                <%--<s:button class="btn" name="reset" onclick="doSubmit(this.form, this.name, 'page_div');" value="Isi Semula"/>--%>
            </p>
        </fieldset>
    </div>
</s:form>
