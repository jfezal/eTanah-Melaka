<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">

    function addNewPemohon(){
        window.open("${pageContext.request.contextPath}/strata/maklumat_pemohon?pemohonPopup", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=750");

    }
    function editPemohon(val){
        window.open("${pageContext.request.contextPath}/strata/maklumat_pemohon?editpemohonPopup&idPihak="+val, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=800,height=300");
    }

</script>
<s:form beanclass="etanah.view.strata.MaklumatSkimActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Skim</legend>
            <p>
                <label>Nama Skim :</label>
                <s:text name="namaSkim" size="40"/>&nbsp;
            </p>
            <p>
                <label>Bilangan Skim :</label>
                <s:text name="bilanganSkim" size="40"/>&nbsp;
            </p>
            <p>
                <label>Bilangan Petak :</label>
                 <s:text name="bilanganPetak" size="40"/>&nbsp;
            </p>
            <p>
                <label>Negeri :</label>
               <s:text name="negeri" size="40"/>&nbsp;
            </p>
             <p>
                <label>Tarikh diluluskan :</label>
               <s:text name="tarikDiluluskan" size="40"/>&nbsp;
            </p>
            <p>
                <label>Tarikh Bayaran kelulusan :</label>
               <s:text name="tarikBayaranKelulusan" size="40"/>&nbsp;
            </p>
            <p>
                <label>Status :</label>
               <s:text name="status" size="40"/>&nbsp;
            </p>
            
        </fieldset >
    </div>
</s:form>