<%-- 
    Document   : Borang_F
    Created on : Aug 12, 2011, 11:34:32 AM
    Author     : shah
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<s:form beanclass="etanah.view.stripes.pelupusan.BorangFActionBean">
    <div class="subtitle">
        <fieldset class="aras1" align="center">
            <s:messages/>
            <s:errors/>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LMCRG' or actionBean.permohonan.kodUrusan.kod eq 'PCRG'}">
            <legend>
                Lesen Mencarigali dan Penjelajahan         
            </legend>
            </c:if>
             <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PJLB'}">
            <legend>
                Lesen Pajakan Lombong        
            </legend>
            </c:if>
            <p>
                <label>Rujukan Fail :</label>
                ${actionBean.idPermohonan}
                &nbsp;

            </p>
            <p>
                <label>No. Pajakan/Pendaftaran :</label>
                ${actionBean.permit.noPermit}
                &nbsp;

            </p>
            <p>
                <label>No. Lot/PT :</label>
                ${actionBean.noLot}
                &nbsp;

            </p>
            <p>
                <label>Bandar/ Kampung/ Mukim :</label>
                ${actionBean.bpm}
       
                &nbsp;

            </p>
            <p>
                <label>Daerah :</label>
                ${actionBean.daerah}

            </p>
            <p>
                <label>Tarikh Pendaftaran :</label>
                <s:format value="${actionBean.tarikhPendaftaran}" formatPattern="dd/MM/yyyy"/>
                &nbsp;
            </p>
            <p>
                <label>Amaun Bayaran :</label>
                RM <s:format formatPattern="###,###,###.00" value="${actionBean.mohonTuntutKos.amaunTuntutan}" />      
            </p>
             <p>
                <label><font color="red">*</font>Tarikh Mula Lesen :</label>
                <s:text name="permitSahLaku.tarikhPermitMula" class="datepicker" formatPattern="dd/MM/yyyy"/>
            </p>
            <p>
                <label><font color="red">*</font>Tarikh Tamat Lesen :</label>
                <s:text name="permitSahLaku.tarikhPermitTamat" class="datepicker" formatPattern="dd/MM/yyyy"/>

            </p>
            <br/><br/>
                <center><s:button name="simpan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name,'page_div')"/></center>
                    
           
            
        </fieldset>
    </div>
</s:form>

