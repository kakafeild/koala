package com.mengke.koala.rebind;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.dev.resource.impl.PathPrefix;
import com.google.gwt.dev.resource.impl.PathPrefixSet;
import com.google.gwt.dev.resource.impl.ResourceOracleImpl;
import com.google.gwt.util.tools.Utility;

import java.io.IOException;
import java.io.PrintWriter;

import static com.google.gwt.core.ext.TreeLogger.Type.DEBUG;
import static com.google.gwt.core.ext.TreeLogger.Type.ERROR;

/**
 * Created by IntelliJ IDEA.
 * User: michaelmeng
 * Date: 12-9-17
 */
public class QueryResultGenerator extends Generator {

	private String javaHeader;
	// cssheader, xmlheader, etc
	private String footer;
	private ResourceOracleImpl sourceOracle;

	@Override
	public String generate(TreeLogger logger, GeneratorContext context, String typeName)
					throws UnableToCompleteException {
		// Get access to metadata about the type to be generated
		TypeOracle oracle = context.getTypeOracle();
		JClassType toGenerate = oracle.findType(typeName).isClass();

		// Get the name of the new type
		String packageName = toGenerate.getPackage().getName();
		String simpleSourceName = toGenerate.getName().replace('.', '_') + "Impl";
		PrintWriter pw = context.tryCreate(logger, packageName, simpleSourceName);
		if (pw == null) {
			return packageName + "." + simpleSourceName;
		}

		// Generate an HTML file resource for every example and write the source
		JClassType[] types = oracle.getTypes();

		// Build a ResourceOracle capable of reading java files
		sourceOracle = new ResourceOracleImpl(logger.branch(DEBUG, "Gathering sources"));

		// Clean up these prefixes to not have filters
		PathPrefixSet prefixes = ((ResourceOracleImpl) context.getResourcesOracle()).getPathPrefixes();
		sourceOracle.setPathPrefixes(new PathPrefixSet());
		for (PathPrefix p : prefixes.values()) {
			sourceOracle.getPathPrefixes().add(new PathPrefix(p.getPrefix(), null));
		}
		ResourceOracleImpl.refresh(logger, sourceOracle);

		// Load the header and footer HTML content
		try {
			String slashyPackageName = getClass().getPackage().getName().replace('.', '/');
			javaHeader = Utility.getFileFromClassPath(slashyPackageName + "/header.html");
			footer = Utility.getFileFromClassPath(slashyPackageName + "/footer.html");
		} catch (IOException e) {
			logger.log(ERROR, "Header or Footer failed to be read", e);
			throw new UnableToCompleteException();
		}



		return null;
	}
}
