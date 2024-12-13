<%--
    Document   : penyediaan_borang4B
    Created on : 5 Sept 2012,12.37 pm 
    Author     : Afham
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

<script type="text/javascript">
    function test(f)
    {
        $(f).clearForm();
    }


</script>


<s:form beanclass="etanah.view.stripes.pelupusan.Borang4BActionBean">
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
                    <td>Lesen Pendudukan Sementara</td>
                    <td><s:format value="${actionBean.permohonanTuntutanBayarLPS.tarikhBayar}" formatPattern="dd/MM/yyyy"/></td>
                    <td>${actionBean.permohonanTuntutanBayarLPS.amaun}</td>
                    <td>${actionBean.permohonanTuntutanBayarLPS.dokumenKewangan.idDokumenKewangan}</td>
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
                <label>No. Lesen :</label>
               <%-- <s:text name="permit.noPermit"/>--%>
               ${actionBean.permit.noPermit}&nbsp;

            </p>
            <br/>
            <p>
                <label><font color="red">*</font>Tarikh Mula Lesen :</label>
                <s:text name="permitSahLaku.tarikhPermitMula" class="datepicker" formatPattern="dd/MM/yyyy"/>
            </p>
            <p>
                <label><font color="red">*</font>Tarikh Tamat Lesen :</label>
                <s:text name="permitSahLaku.tarikhPermitTamat" class="datepicker" formatPattern="dd/MM/yyyy"/>

            </p>
            <p>
                <label>Peruntukan Tambahan :</label>
                <s:textarea name="peruntukanTambahan" rows="5" cols="40"/>
            </p>
            <br>
            <c:if test="${actionBean.view}">
              <p>
            <label>&nbsp;</label>

            <s:button name="simpanPermit" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name,'page_div')"/>
            
        </p>
        </c:if>
        </fieldset>
    </div>
 
</s:form>
