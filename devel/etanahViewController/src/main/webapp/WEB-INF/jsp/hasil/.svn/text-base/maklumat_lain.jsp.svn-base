<%-- 
    Document   : maklumat_lain
    Created on : Nov 12, 2009, 5:56:13 PM
    Author     : abu.mansur
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%--<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>.: e-Tanah - Hasil :.</title>        
    </head>
    <body>--%>
        <script type="text/javascript">
            $(document).ready(function() {
                $('#lain').hide();

            var v = '${actionBean.permohonan.sebab}';
            if(v == 'LLS')
                $('#lain').show();
            });

            function changeLain(value){
                if(value=="LLS")
                    $('#lain').show();
                else
                    $('#lain').hide();
            }
        </script>
        <s:form beanclass="etanah.view.stripes.hasil.MaklumatLainActionBean">
        <div class="subtitle">
            <br>
            <fieldset class="aras1">
            
                <p>
                    <s:messages />
                    <s:errors />&nbsp;
                </p>
                <p>
                    <label>Asas Rayuan :</label>
                    <s:radio name="permohonan.sebab" value="TMB" onclick="javaScript:changeLain(this.value)"/> TIDAK MENERIMA BIL
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:radio name="permohonan.sebab" value="TMMBP" onclick="javaScript:changeLain(this.value)"/> TIDAK MAMPU MENJELASKAN BAYARAN PENUH
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:radio name="permohonan.sebab" value="LLS" onclick="javaScript:changeLain(this.value)"/> LAIN-LAIN SEBAB&nbsp;
                </p>
                <p id="lain">
                    <label>&nbsp;</label>
                    <s:textarea name="lainSebab" cols="40" rows="4"/>
                </p>
                    <%--<s:select id="rayuan" name="permohonan.sebab" style="width:160px;" onchange="sebabLain(this.value);">
                        <s:option value="">Sila Pilih...</s:option>
                        <s:options-collection collection="${actionBean.senaraiKodAgensi}" label="nama" value="kod" />
                        <s:option value=" ">Sila Pilih...</s:option>
                        <s:option value="0101">TIDAK MENERIMA BIL</s:option>
                        <s:option value="0202">TIDAK MAMPU MENJELASKAN BAYARAN PENUH</s:option>
                        <s:option value="9999">LAIN-LAIN SEBAB</s:option>
                    </s:select>
                </p>
                <p id="lain">
                    <label>Lain-Lain Sebab :</label>
                    <s:textarea name="permohonan.sebab" cols="30" rows="3"/>
                    <s:button id="lain" value="testing" name=""/>--%>                
                <%--<p>
                    <label>Pengurangan Melebihi 50% :</label>
                    <s:radio name="kurang" value="Y"/>Ya &nbsp;&nbsp;
                    <s:radio name="kurang" value="T"/>Tidak
                    <s:button id="lain" value="testing" name=""/>
                </p>--%>
            </fieldset>
                <br>
<!--            <fieldset class="aras1">
                <legend>Keputusan</legend>
                <p id="lain">
                    <label>Nilai Keputusan : </label>
                    <s:textarea name="nilai" cols="40" rows="4"/> %
                </p>
            </fieldset>-->
            <table width="96%" style="${actionBean.display}">
                <tr>
                    <%--<s:hidden name="permohonanUlasan.tarikhUlasan" value="<%=new java.util.Date()%>" />--%>
                    <td align="right">
                        <s:button class="btn" onclick="doSubmit(this.form, this.name, 'page_div');" name="saveOrUpdate" value="Simpan"/>&nbsp;
                        <%--<s:reset class="btn" name="reset" value="Isi Semula"/>--%>
                    </td>
                </tr>
            </table>
            <%--</s:form>--%>
        </div>
            </s:form>
<%--    </body>
</html>--%>
