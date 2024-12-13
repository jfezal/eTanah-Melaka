<%-- 
    Document   : kadar_pengurangan
    Created on : Jan 28, 2010, 10:35:27 AM
    Author     : abu.mansur
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    $(document).ready(function() {
        var urusan = '${actionBean.permohonan.kodUrusan.kod}';
        if(urusan != "REMTD"){
            var v = '${actionBean.denda}';
            var v1 = '${actionBean.permohonan.nilaiKeputusan}';
            v1 = v * v1;
            $('#jumlah').val(Math.floor(v1).toFixed(2)); //BigDecimal.Round_Down
            $('#denda').val((v - Math.floor(v1)).toFixed(2));
        }else{
            var jumBaki = '${actionBean.akaun.baki}';
            var jumKurang = '${actionBean.permohonan.nilaiKeputusan}';
            jumBaki = jumBaki - jumKurang;
            $('#kurang').val(Math.floor(jumKurang).toFixed(2)); //BigDecimal.Round_Down
            $('#baki').val(Math.floor(jumBaki).toFixed(2));
        }
    });

    function changePercentage(value){
        var v = '${actionBean.denda}';
        var v1 = 0;
        if(value=="0.1"){
            v1 = v * 0.1;
            $('#jumlah').val(Math.floor(v1).toFixed(2));
            $('#denda').val((v - Math.floor(v1)).toFixed(2));
        }
        if(value=="0.2"){
            v1 = v * 0.2;
            $('#jumlah').val(Math.floor(v1).toFixed(2));
            $('#denda').val((v - Math.floor(v1)).toFixed(2));
        }
        if(value=="0.3"){
            v1 = v * 0.3;
            $('#jumlah').val(Math.floor(v1).toFixed(2));
            $('#denda').val((v - Math.floor(v1)).toFixed(2));
        }
        if(value=="0.4"){
            v1 = v * 0.4;
            $('#jumlah').val(Math.floor(v1).toFixed(2));
            $('#denda').val((v - Math.floor(v1)).toFixed(2));
        }
        if(value=="0.5"){
            v1 = v * 0.5;
            $('#jumlah').val(Math.floor(v1).toFixed(2));
            $('#denda').val((v - Math.floor(v1)).toFixed(2));
        }
    }

    function getJumKurang(value){
        var jumBaki = '${actionBean.akaun.baki}';
        var jumKurang = value;
        jumBaki = jumBaki - jumKurang;
        $('#kurang').val(Math.floor(jumKurang).toFixed(2)); //BigDecimal.Round_Down
        $('#baki').val(Math.floor(jumBaki).toFixed(2));
    }
</script>
<s:form beanclass="etanah.view.stripes.hasil.KadarPenguranganActionBean">
    <div class="subtitle">
        <br>
        <fieldset class="aras1">

            <p>
                <s:messages />
                <s:errors />&nbsp;
            </p>
            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'REMTD'}">
                <p>
                    <label>Kadar :</label>
                    <s:radio name="permohonan.nilaiKeputusan" value="0.1" onclick="javaScript:changePercentage(this.value)"/> 10%
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
                <%--<p id="lain">
                    <label>&nbsp;</label>
                    <s:textarea name="lainSebab" cols="30" rows="3"/>
                </p>--%>
                <p>
                    <label>Amaun Kadar Pengurangan (RM) :</label>
                    <s:text name="xxx" disabled="true" style="text-align:right;" id="jumlah" size="15"/>
                </p>
                <p>
                    <label>Amaun Denda Semasa (RM) :</label>
                    <s:text name="xxx" disabled="true" style="text-align:right;" id="denda" size="15"/>
                </p>
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'REMTD'}">
                <p>
                    <label>Jumlah Pengurangan (RM) :</label>
                    <s:text name="permohonan.nilaiKeputusan" value="0.00" formatPattern="#,##0.00" onblur="getJumKurang(this.value);" style="text-align:right;" id="kurang" size="15"/>
                </p>
                <p>
                    <label>Jumlah Keseluruhan (RM) :</label>
                    <s:text name="xxx" disabled="true" style="text-align:right;" id="baki" size="15"/>
                </p>
            </c:if>
            <p align="right">
                <s:button class="btn" onclick="doSubmit(this.form, this.name, 'page_div');" name="saveOrUpdate" value="Simpan"/>&nbsp;
                <%--<s:button class="btn" name="reset" onclick="doSubmit(this.form, this.name, 'page_div');" value="Isi Semula"/>--%>
            </p>
        </fieldset>
    </div>
</s:form>