<%-- 
    Document   : dokumen_sign
    Created on : Jan 6, 2010, 8:33:33 AM
    Author     : fikri
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript">
    function signFiles(fileNme,fldrNme,txtJawatan,template,signature){

        var signer = new ActiveXObject("SAPDFSigner.Form1");

        signer.SigningPDFFile(fileNme,fldrNme,txtJawatan,template,signature);

        if(signer.getValue()!=""){
            document.eload.message.value = signer.getValue();
        }

        if(document.eload.message.value!="undefined"){
            //var urls='index.php?task=process&filename='+fileNme;
            var urls = '${pageContext.request.contextPath}lelong/dokumen/view/' + fileNme + '?signForm&afterSign=true';
            document.eload.action = urls;
            document.eload.submit();
        }
    }

    function signFilesVDoc(fileNme,fldrNme,txtJawatan,template,signature){

        var signer = new ActiveXObject("SAPDFSigner.Form1");

        //signer.SigningPDFFile(fileNme,fldrNme,txtJawatan,template,signature);
        signer.SigningPDFFile(fileNme, fldrNme, txtJawatan, 340, 590, 430, 620, template,1, signature)
        if(signer.getValue()!=""){
            document.eload.message.value = signer.getValue();
        }

        if(document.eload.message.value!="undefined"){
            var urls = '${pageContext.request.contextPath}lelong/dokumen/view/' + fileNme + '?signForm&afterSign=true&vdoc=true';
            document.eload.action = urls;
            document.eload.submit();
        }
    }

    function signFilesB1(fileNme,fldrNme,txtJawatan,template,signature){

	var signer = new ActiveXObject("SAPDFSigner.Form1");

			//signer.SigningPDFFile(fileNme,fldrNme,txtJawatan,template,signature);
	signer.SigningPDFFile(fileNme, fldrNme, txtJawatan, 420, 80, 540, 140, template,1, signature)
		if(signer.getValue()!=""){

			document.eload.message.value = signer.getValue();

		}

		if(document.eload.message.value!="undefined"){
			var urls = '${pageContext.request.contextPath}lelong/dokumen/view/' + fileNme + '?signForm&afterSign=true';
                        document.eload.action = urls;
                        document.eload.submit();
		}
	}

     function signFiles(fileNme,fldrNme){

					var signer = new ActiveXObject("SAPDFSigner.Form1");

							signer.SigningMultiPDFFile(fileNme,fldrNme);

						if(signer.getValue()!=""){

							document.eload.message.value = signer.getValue();

						}

						if(document.eload.message.value!="undefined"){
							var urls = '${pageContext.request.contextPath}lelong/dokumen/view/' + fileNme + '?signForm&afterSign=true';
							document.eload.action = urls;
							document.eload.submit();
						}
					}

    function verifyDS(fleName,fileNme){
        var signer = new ActiveXObject("SAPDFSigner.Form1");

        signer.verifyDS(fleName+'.sig',fileNme);

    }
</script>
<s:form beanclass="etanah.view.lelong.dokumen.ViewDocumentAction" name="eload">
    <s:hidden name="message"/>
    <s:hidden name="vdoc" value="${vDoc}"/>
    <br/>
    <p>
        <label>&nbsp;</label>
        <c:if test="${!empty vDoc}">
            <s:button name="" value="T/Tangan" class="btn" onclick="signFilesVDoc('${actionBean.idDokumen}','${actionBean.idFolder}','${actionBean.pengguna.nama}','template2','user2.gif');"/>
        </c:if>
        <c:if test="${empty vDoc}">
            <s:button name="" value="T/Tangan" class="btn" onclick="signFilesB1('${actionBean.idDokumen}','${actionBean.idFolder}','${actionBean.pengguna.nama}','template2','user2.gif');"/>
        </c:if>
        <%--<s:button name="" value="T/Tangan" class="btn" onclick="signFiles('${actionBean.idDokumen}','${actionBean.idFolder}')"/>--%>


    </p>   
    <p>
        <label>&nbsp;</label>
        <iframe src="${pageContext.request.contextPath}/dokumen/view/${actionBean.idDokumen}?viewPdf&afterSign=${afterSign}" width="98%" height="560"/>
    </p>

    <%--<img src="${pageContext.request.contextPath}/dokumen/view/${actionBean.idDokumen}?viewPdf" width="77" height="77"/>--%>

</s:form>
