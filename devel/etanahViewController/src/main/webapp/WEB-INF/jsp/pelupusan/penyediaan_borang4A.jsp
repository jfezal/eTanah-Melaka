<%--
    Document   : penyediaan_borang4A
    Created on : May 7, 2010, 5:28:52 PM
    Author     : nurul.izza
    modified by: sitifariza.hanim on 11102010
    Modified by: Murali on 23/05/2011
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


<s:form beanclass="etanah.view.stripes.pelupusan.Borang4AActionBean">
 <s:hidden name="lupusPermit.id"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <s:messages/>
            <s:errors/>
            <legend>
                Maklumat Bayaran
            </legend>
           
            <p>
                <label>Bayaran (RM) :</label>
                <%--<s:text name="lupusPermit.bayaran" value="${actionBean.bayaran}"/>--%>
                ${actionBean.permohonanTuntutanBayar.amaun}&nbsp;

            </p>
            <p>
                <label>Nombor Resit :</label>
                <%--<s:text name="lupusPermit.noResit" size="15" value="${actionBean.noResit}" />--%>
                ${actionBean.permohonanTuntutanBayar.dokumenKewangan.idDokumenKewangan}&nbsp;
            </p>
            <p>
                <label>Tarikh Bayaran :</label>
               <%--${actionBean.permohonanTuntutanBayar.tarikhBayar}&nbsp;--%>
                <fmt:formatDate value="${actionBean.permohonanTuntutanBayar.tarikhBayar}" pattern="dd/MM/yyyy "/>&nbsp;
            </p>
            <br>             
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
            <p>
                
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPTD'}">
                    <c:if test="${actionBean.stageId ne '22PnyedianBrg4A'}">
                        <label>Tarikh Mula Lesen :</label>
                         <fmt:formatDate value="${actionBean.permitSahLaku.tarikhPermitMula}" pattern="dd/MM/yyyy "/>&nbsp;
                    </c:if>
                    <c:if test="${actionBean.stageId eq '22PnyedianBrg4A'}">
                        <label><font color="red">*</font>Tarikh Mula Lesen :</label>
                        <s:text name="permitSahLaku.tarikhPermitMula" class="datepicker" formatPattern="dd/MM/yyyy"/>
                    </c:if>
                </c:if>
                        
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'RLPS'}">
                    <c:if test="${actionBean.stageId ne '18SedBrg4A'}">
                        <label>Tarikh Mula Lesen :</label>
                         <fmt:formatDate value="${actionBean.permitSahLaku.tarikhPermitMula}" pattern="dd/MM/yyyy "/>&nbsp;
                    </c:if>
                    <c:if test="${actionBean.stageId eq '18SedBrg4A'}">
                        <label><font color="red">*</font>Tarikh Mula Lesen :</label>
                        <s:text name="permitSahLaku.tarikhPermitMula" class="datepicker" formatPattern="dd/MM/yyyy"/>
                    </c:if>
                </c:if>
                        
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PLPTD' and actionBean.permohonan.kodUrusan.kod ne 'RLPS'}">
                    <label><font color="red">*</font>Tarikh Mula Lesen :</label>
                    <s:text name="permitSahLaku.tarikhPermitMula" class="datepicker" formatPattern="dd/MM/yyyy"/>
                </c:if>
            </p>
            <p>
                
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PLPTD' and actionBean.permohonan.kodUrusan.kod ne 'RLPS'}">
                    <label><font color="red">*</font>Tarikh Tamat Lesen :</label>
                 <s:text name="permitSahLaku.tarikhPermitTamat" formatPattern="dd/MM/yyyy" class="datepicker" readonly="true"/>   
                </c:if>
                    
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPTD' or actionBean.permohonan.kodUrusan.kod eq 'RLPS' }">
                 
                    <c:if test="${actionBean.stageId ne '22PnyedianBrg4A' and actionBean.stageId ne '18SedBrg4A'}">
                        <label>Tarikh Tamat Lesen :</label>
                        <fmt:formatDate value="${actionBean.permitSahLaku.tarikhPermitTamat}" pattern="dd/MM/yyyy "/>&nbsp;
                    </c:if>
                    <c:if test="${actionBean.stageId eq '22PnyedianBrg4A' or actionBean.stageId eq '18SedBrg4A'}">
                        <label><font color="red">*</font>Tarikh Tamat Lesen :</label>
                        <s:text name="permitSahLaku.tarikhPermitTamat" formatPattern="dd/MM/yyyy" class="datepicker"/>  
                    </c:if>
                </c:if>
                
            </p>
            <p>
                <label>Peruntukan Tambahan :</label>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPTD'}">
                    <c:if test="${actionBean.stageId ne '22PnyedianBrg4A'}">
                        ${actionBean.peruntukanTambahan}
                    </c:if>
                    <c:if test="${actionBean.stageId eq '22PnyedianBrg4A'}">
                        <s:textarea name="peruntukanTambahan" rows="5" cols="40"/>
                    </c:if>
                </c:if>
                
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'RLPS'}">
                    <c:if test="${actionBean.stageId ne '18SedBrg4A'}">
                        ${actionBean.peruntukanTambahan}
                    </c:if>
                    <c:if test="${actionBean.stageId eq '18SedBrg4A'}">
                        <s:textarea name="peruntukanTambahan" rows="5" cols="40"/>
                    </c:if>
                </c:if>
                
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PLPTD' and actionBean.permohonan.kodUrusan.kod ne 'RLPS'}">
                    <s:textarea name="peruntukanTambahan" rows="5" cols="40"/>
                </c:if>
                
            </p>
            <br>
              <p>
            <label>&nbsp;</label>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPTD'}">
                    <c:if test="${actionBean.stageId eq '22PnyedianBrg4A'}">
                        <s:button name="simpanPermit" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name,'page_div')"/>
                    </c:if>
                </c:if>
            
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'RLPS'}">
                    <c:if test="${actionBean.stageId eq '18SedBrg4A'}">
                        <s:button name="simpanPermit" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name,'page_div')"/>
                    </c:if>
                </c:if>
            
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS'}">
                    <c:if test="${actionBean.stageId eq '44PenyediaanBrg4ae'}">
                        <s:button name="simpanPermit" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name,'page_div')"/>
                    </c:if>
                </c:if>

            <c:if test="${simpan}">
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PLPTD' and actionBean.permohonan.kodUrusan.kod ne 'RLPS' and actionBean.permohonan.kodUrusan.kod ne 'PLPS'}">
                    <s:button name="simpanPermit" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name,'page_div')"/>
                </c:if>
            </c:if>
            
        </p>
        </fieldset>
    </div>
</s:form>
