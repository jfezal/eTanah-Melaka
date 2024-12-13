<%--
    Document   : partial_kodbpm2
    Created on : Mar 24, 2010, 11:34:17 AM
    Author     : khairil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    $(document).ready( function(){
        $('#kodBpm').val($('#kodBPM').val());
        var kodUrusan = '${actionBean.p.kodUrusan.kod}';
       
        //alert(kodUrusan);
        var pengPTG = '${actionBean.pengPTG}';
        <%--if(kodUrusan != 'HSTK' && kodUrusan != 'HKTK' && kodUrusan != 'HKTKP' && kodUrusan != 'HSCTK' && kodUrusan != 'HKCTK' && kodUrusan != 'HKPTK'
             && kodUrusan != 'HSBTK' && kodUrusan != 'HKABT' && kodUrusan != 'HKBTK' && kodUrusan != 'HKSTK' && kodUrusan != 'HSPTK' ){--%>
            $('#kodBpm').attr("disabled", "disabled");
       <%-- }else{
             $('#kodBpm').attr("disabled", "");
        }--%>
        
    });
</script>
<s:form partial="true" beanclass="etanah.view.stripes.KemasukanPerincianHakmilikActionBean">
   <%-- <c:if test="${actionBean.p.kodUrusan.kod ne 'HSBM' || actionBean.p.kodUrusan.kod ne 'HKBM'}">
        <c:set var="disabled" value=""/>
    </c:if>
    <c:if test="${actionBean.p.kodUrusan.kod eq 'HSBM' || actionBean.p.kodUrusan.kod eq 'HKBM'}">
        <c:set var="disabled" value="disabled"/>
    </c:if>
    <c:if test="${actionBean.pengPTG eq false}">
        <c:set var="disabled" value="disabled"/>
    </c:if>--%>
    <p>
        <label>Bandar / Pekan / Mukim :</label>
        <c:if test="${disabled eq 'disabled'}" >
            <s:hidden id="kodBpm" value="${actionBean.hakmilik.bandarPekanMukim.bandarPekanMukim}" name="hakmilik.bandarPekanMukim.bandarPekanMukim"/>
        </c:if>

        <s:select disabled="${disabled}" style="text-transform:uppercase;width:200pt" name="kodBPM" id="kodBpm"
                  onchange="kiraCukai(this.form,'${actionBean.hakmilik.idHakmilik}');
                  filterKodSeksyen();">
            <%--<s:option value="">Sila Pilih</s:option>--%>
            <s:options-collection collection="${actionBean.listBandarPekanMukim}" label="nama" value="bandarPekanMukim" />
        </s:select>

    </p>
</s:form>
