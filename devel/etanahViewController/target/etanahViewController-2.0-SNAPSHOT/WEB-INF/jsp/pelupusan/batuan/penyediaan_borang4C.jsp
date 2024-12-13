<%-- 
    Document   : penyediaan_borang4C
    Created on : Jun 20, 2011, 10:33:34 AM
    Author     : Akmal
--%>

<%--<%@page contentType="text/html" pageEncoding="windows-1252"%>--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

<script type="text/javascript">
    function test(f)
    {
        $(f).clearForm();
    }


</script>


<s:form beanclass="etanah.view.stripes.pelupusan.Borang4CBatuanActionBean">
 <s:hidden name="lupusPermit.id"/>
 <s:messages/>
 <s:errors/>

    <div class="subtitle">
        <fieldset class="aras1">
            
            <legend>
                Maklumat Bayaran
            </legend>
           
            <table align="center" class="tablecloth">
                <tr>
                <th>Item</th>
                <th>Tarikh Bayaran</th>
                <th>Bayaran</th>
                <th>No Resit</th>
                </tr>
                <tr>
                    <td>Royalti</td>
                    <td><s:format value="${actionBean.permohonanTuntutanBayar.tarikhBayar}" formatPattern="dd/MM/yyyy"/></td>
                    <td>${actionBean.permohonanTuntutanBayar.amaun}</td>
                    <td>${actionBean.permohonanTuntutanBayar.dokumenKewangan.idDokumenKewangan}</td>
                </tr>
                <tr>
                    <td>Wang Cagaran</td>
                    <td><s:format value="${actionBean.permohonanTuntutanBayar1.tarikhBayar}" formatPattern="dd/MM/yyyy"/></td>
                    <td>${actionBean.permohonanTuntutanBayar1.amaun}</td>
                    <td>${actionBean.permohonanTuntutanBayar1.dokumenKewangan.idDokumenKewangan}</td>
                </tr>
                 <tr>
                    <td>Doket/Kupon</td>
                    <td><s:format value="${actionBean.permohonanTuntutanBayar2.tarikhBayar}" formatPattern="dd/MM/yyyy"/></td>
                    <td>${actionBean.permohonanTuntutanBayar2.amaun}</td>
                    <td>${actionBean.permohonanTuntutanBayar2.dokumenKewangan.idDokumenKewangan}</td>
                </tr>
                <tr>
                    <td>Cagaran Jalan</td>
                    <td><s:format value="${actionBean.permohonanTuntutanBayar3.tarikhBayar}" formatPattern="dd/MM/yyyy"/></td>
                    <td>${actionBean.permohonanTuntutanBayar3.amaun}</td>
                    <td>${actionBean.permohonanTuntutanBayar3.dokumenKewangan.idDokumenKewangan}</td>
                </tr>
            </table>
        </fieldset>
    </div>
  
    <br>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Jadual
            </legend>
             <p>
                <label>No. Permit :</label>
               <%-- <s:text name="permit.noPermit"/>--%>
               ${actionBean.permit.noPermitBaru}&nbsp;

            </p>
            <br/>
            <p>
                <label><font color="red">*</font>Tarikh Mula Permit :</label>
                <c:if test="${actionBean.view}"><s:format value="${actionBean.permitSahLaku.tarikhPermitMula}" formatPattern="dd/MM/yyyy"/></c:if>
                <c:if test="${!actionBean.view}"><s:text name="permitSahLaku.tarikhPermitMula" class="datepicker" formatPattern="dd/MM/yyyy"/></c:if>
                
            </p>
            <p>
                <label><font color="red">*</font>Tarikh Tamat Permit :</label>
                <c:if test="${actionBean.view}"><s:format value="${actionBean.permitSahLaku.tarikhPermitTamat}" formatPattern="dd/MM/yyyy"/></c:if>
                <c:if test="${!actionBean.view}"><s:text name="permitSahLaku.tarikhPermitTamat" class="datepicker" formatPattern="dd/MM/yyyy"/></c:if>

            </p>
            <p>
                <label>Peruntukan Tambahan :</label>
            <c:if test="${actionBean.view}"><s:textarea name="peruntukanTambahan" rows="5" cols="40" class="normal_text" readonly="true"/></c:if>
                <c:if test="${!actionBean.view}"><s:textarea name="peruntukanTambahan" rows="5" cols="40" class="normal_text"/></c:if>
                
            </p>
            <br>
              <p>
            <label>&nbsp;</label>
                <c:if test="${!actionBean.view}"><s:button name="simpanPermit" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name,'page_div')"/></c:if>
            
        </p>
        </fieldset>
    </div>
 
</s:form>
